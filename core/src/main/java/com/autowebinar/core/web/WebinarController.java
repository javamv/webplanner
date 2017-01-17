package com.autowebinar.core.web;

import com.autowebinar.core.data.User;
import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.utils.ModelUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping()
public class WebinarController extends BasicWebController {

    @GetMapping(value="/main")
    public String createWebinar(Model model) {

        User user = getCurrentUser();
        ModelUtils.userToModel(model, user);

        return "main";
    }

    @RequestMapping(value="/createWebinar", method = RequestMethod.POST)
    public String createWebinar(Model model, HttpServletRequest request) {
        String topicEng = request.getParameter("topic_eng");
        String descriptionEng = request.getParameter("description_eng");
        String imageLink = request.getParameter("imageLink");
        String targetAudience = request.getParameter("targetAudience");
        String language = request.getParameter("language");

        User user = getCurrentUser();

        Webinar webinar = new Webinar(topicEng, descriptionEng, user.getId(), imageLink, language, targetAudience);
        // save
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar);

        return "webinar";
    }

    @GetMapping("/retrieveWebinar")
    public String retrieveWebinar(@RequestParam("id") String id, Model model) {
        Webinar webinar = getWebinar(id);
        ModelUtils.webinarToModel(model, webinar);
        return "webinar";
    }

    @GetMapping("/removeWebinar")
    @ResponseBody
    public String removeWebinar(@RequestParam("id") String id, Model model) {
        Query searchWebinarQuery = new Query(Criteria.where("id").is(id));
        mongoOperations.remove(searchWebinarQuery, Webinar.class);
        return "success";
    }

}
