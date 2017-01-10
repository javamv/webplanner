package com.autowebinar.core.web;

import com.autowebinar.core.data.User;
import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping()
public class WebinarController {

    @Autowired
    MongoOperations mongoOperations;

    @GetMapping(value="/main")
    public String createWebinar(@RequestParam(value = "id") String id, Model model) {

        Query searchUserQuery = new Query(Criteria.where("id").is(id));
        User user = mongoOperations.findOne(searchUserQuery, User.class);

        ModelUtils.userToModel(model, user);

        return "main";
    }

    @RequestMapping(value="/createWebinar", method = RequestMethod.POST)
    public String createWebinar(Model model, HttpServletRequest request) {
        String topicEng = request.getParameter("topic_eng");
        String descriptionEng = request.getParameter("description_eng");
        String userId = request.getParameter("userId");

        Webinar webinar = new Webinar(topicEng, descriptionEng, userId);
        // save
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, mongoOperations);

        return "webinar";
    }

    @RequestMapping(value="/retrieveWebinar", method = RequestMethod.GET)
    public String retrieveWebinar(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");

        Query searchUserQuery = new Query(Criteria.where("id").is(id));
        Webinar webinar = mongoOperations.findOne(searchUserQuery, Webinar.class);

        ModelUtils.webinarToModel(model, webinar, mongoOperations);

        return "webinar";
    }

}
