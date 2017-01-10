package com.autowebinar.core.web;

import com.autowebinar.core.confluence.ConfluenceRestSession;
import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * Created by VMoskalenko on 06.01.2017.
 */
@Controller
public class ConfluenceController {

    @Autowired
    ConfluenceRestSession confluenceRestSession;

    private static String title = "Webinar from Luxoft Agile Practice";

    @Autowired
    private MongoOperations mongoOperations;

    @GetMapping("/createBlogPost")
    public String createBlogPost(@RequestParam(value = "id") String id, Model model) throws IOException {

        Query searchUserQuery = new Query(Criteria.where("id").is(id));
        Webinar webinar = mongoOperations.findOne(searchUserQuery, Webinar.class);

        ConfluenceRestSession.BlogPost blogPost = new ConfluenceRestSession.BlogPost(
                "blogpost",
                webinar.getTopicEng(),
                new ConfluenceRestSession.Space("~vmoskalenko"),
                new ConfluenceRestSession.Body(new ConfluenceRestSession.Storage(
                        webinar.getDescriptionEng(), "storage")));

        String blogPostCode = confluenceRestSession.writeBlogPost(blogPost);

        webinar.setPosted(true);
        webinar.setBlogPostCode(blogPostCode);
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, mongoOperations);

        return "webinar";
    }

}