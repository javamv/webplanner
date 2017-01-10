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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by VMoskalenko on 10.01.2017.
 *
 */
@Controller
public class AuthController {

    @Autowired
    MongoOperations mongoOperations;

    @PostMapping("auth")
    @ResponseBody
    public String authenticate(@RequestParam(value = "id") String id,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "image") String image) {

        if (id == null || id.isEmpty()) {
            return "404";
        }

        Query searchUserQuery = new Query(Criteria.where("googleId").is(id));
        User user = mongoOperations.findOne(searchUserQuery, User.class);

        if (user == null) {
            user = new User(name, email, image, id);
            mongoOperations.save(user);
        }

        return user.getId();
    }

    @PostMapping("profile")
    public String updateProfile(@RequestParam(value = "userId") String id,
                               @RequestParam(value = "luxEmail") String luxMail,
                               @RequestParam(value = "signature") String signature,
                               Model model) {

        Query searchUserQuery = new Query(Criteria.where("id").is(id));
        User user = mongoOperations.findOne(searchUserQuery, User.class);

        user.setLuxMail(luxMail);
        user.setSignature(signature);

        mongoOperations.save(user);

        ModelUtils.userToModel(model, user);

        return "main";
    }

}
