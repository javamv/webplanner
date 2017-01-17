package com.autowebinar.core.utils;

import static com.autowebinar.core.data.ConstantVariables.*;

import com.autowebinar.core.data.User;
import com.autowebinar.core.data.Webinar;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;

/**
 * Created by VMoskalenko on 06.01.2017.
 */
public class ModelUtils {

    public static void webinarToModel(Model model, Webinar webinar) {

        model.addAttribute("topic", webinar.getTopicEng());
        model.addAttribute("description", webinar.getDescriptionEng());
        model.addAttribute("id", webinar.getId());
        model.addAttribute("scheduled", webinar.getScheduled());
        model.addAttribute("posted", webinar.getPosted());
        model.addAttribute("sentForApproval", webinar.getSentForApproval());
        model.addAttribute("gotoLink", createGotoLink(webinar));
        model.addAttribute("gotoManageLink", createManageLink(webinar));
        model.addAttribute("blogLink", luxtownViewLink(webinar));
        model.addAttribute("blogEditLink", luxtownEditLink(webinar));
    }

    public static void webinarToModel(Model model, Webinar webinar, User currentUser) {
        webinarToModel(model, webinar);
        model.addAttribute("userImage", currentUser.getImage());
        model.addAttribute("userId", currentUser.getId());

    }

    public static void userToModel(Model model, User user) {
        model.addAttribute("userId", user.getId());
        model.addAttribute("userImage", user.getImage());
        model.addAttribute("luxEmail", user.getLuxMail());
        model.addAttribute("signature", user.getSignature());
    }
}
