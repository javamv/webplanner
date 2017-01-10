package com.autowebinar.core.web;

import com.autowebinar.core.data.User;
import com.autowebinar.core.data.Webinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@ResponseBody
public class DataController {

    @Autowired
    MongoOperations mongoOperations;

    @RequestMapping("/webinars")
    @ResponseBody
    public List<Webinar> webinars() {
        System.out.println("reached");
        return mongoOperations.findAll(Webinar.class);
    }

    @RequestMapping("/users")
    @ResponseBody
    public List<User> users() {
        return mongoOperations.findAll(User.class);
    }
}