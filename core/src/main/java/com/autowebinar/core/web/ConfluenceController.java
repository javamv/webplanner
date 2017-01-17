package com.autowebinar.core.web;

import com.autowebinar.core.confluence.ConfluenceRestSession;
import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.data.WebinarLog;
import com.autowebinar.core.utils.ModelUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.autowebinar.core.data.ConstantVariables.createGotoLinkLT;

/**
 * Created by VMoskalenko on 06.01.2017.
 *
 */
@Controller
public class ConfluenceController extends BasicWebController {

    @Autowired
    VelocityEngine velocityEngine;

    @Autowired
    private MongoOperations mongoOperations;

    @PostMapping("/createBlogPost")
    public String createBlogPost(@RequestParam(value = "id") String id,
                                 @RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password,
                                 Model model) throws IOException {

        Webinar webinar = getWebinar(id);

        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        startTime.setTime(webinar.getStartDate());

        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        endTime.setTime(webinar.getEndDate());


        VelocityContext context = new VelocityContext();
        Template body = velocityEngine.getTemplate("velocity/blogpost.vh", "UTF-16");
        context.put("description", webinar.getDescriptionEng());
        context.put("image", webinar.getImageLink());
        context.put("time", String.format(Locale.ENGLISH,
                "Date: %1$tB %1$te, Time: %1$tI p.m. - %2$tI p.m. (Moscow time zone)",
                startTime,
                endTime));
        context.put("gotoLink", createGotoLinkLT(webinar));
        StringWriter writer = new StringWriter();
        body.merge(context, writer);

        ConfluenceRestSession.BlogPost blogPost = new ConfluenceRestSession.BlogPost(
                "blogpost",
                webinar.getTopicEng(),
                new ConfluenceRestSession.Space("~"+username),
                new ConfluenceRestSession.Body(new ConfluenceRestSession.Storage(
                        writer.toString(), "storage")));

        ConfluenceRestSession confluenceRestSession = new ConfluenceRestSession(username, password);
        String blogPostCode = confluenceRestSession.writeBlogPost(blogPost);

        webinar.setPosted(true);
        webinar.setBlogPostCode(blogPostCode);
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar);

        mongoOperations.save(new WebinarLog(webinar.getId(), new Date(), 1L));

        return "webinar";
    }

    @PostMapping("/deleteBlogPost")
    public String deleteBlogPost(@RequestParam(value = "id") String id,
                                 @RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password,
                                 Model model) throws IOException {

        Webinar webinar = getWebinar(id);

        ConfluenceRestSession confluenceRestSession = new ConfluenceRestSession(username, password);
        confluenceRestSession.deleteBlogPost(webinar.getBlogPostCode());

        webinar.setPosted(false);
        webinar.setBlogPostCode(null);
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar);

        return "webinar";
    }

}