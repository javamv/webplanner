package com.autowebinar.core.web;

import com.autowebinar.core.data.User;
import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.utils.ModelUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.autowebinar.core.data.ConstantVariables.*;

/**
 * Created by VMoskalenko on 09.01.2017.
 *
 */
@Controller
public class SendEmailController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MongoOperations mongoOperations;


    @Autowired
    VelocityEngine velocityEngine;

    @GetMapping("/notifyTC")
    public String notifyTC(@RequestParam(value = "id") String id, Model model) throws MessagingException {

        Query searchWebinarQuery = new Query(Criteria.where("id").is(id));
        Webinar webinar = mongoOperations.findOne(searchWebinarQuery, Webinar.class);

        Query searchUserQuery = new Query(Criteria.where("id").is(webinar.getUserId()));
        User user  = mongoOperations.findOne(searchUserQuery, User.class);

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(LUXOFT_AGILE_GMAIL_COM);
        helper.setCc(user.getLuxMail());
        helper.setTo(VMOSKALENKO_LUXOFT_COM);
        helper.setSubject(EMAIL_SUBJECT);

        VelocityContext context = new VelocityContext();
        Template body = velocityEngine.getTemplate("velocity/tcbody.ve", "UTF-16");
        context.put("signature", user.getSignature());
        StringWriter writer = new StringWriter();
        body.merge(context, writer);
        helper.setText(writer.toString(), false);

        /*  Attachement  */
        String gotoUrl = createGotoLinkTC(webinar);

        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        startTime.setTime(webinar.getStartDate());

        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        endTime.setTime(webinar.getEndDate());

        Template attachment = velocityEngine.getTemplate("velocity/tctemplate.mht", "UTF-8");
        context = new VelocityContext();
        context.put("description", webinar.getDescriptionEng());
        context.put("topic", webinar.getTopicEng());
        context.put("date", String.format(Locale.ENGLISH, "%1$tB %1$te", webinar.getStartDate()));
        context.put("time", String.format(Locale.ENGLISH, "%1$tI p.m. - %2$tI p.m. (Moscow time zone)",
                startTime, endTime));
        context.put("link", gotoUrl);
        context.put("instructor", user.getUsername());
        writer = new StringWriter();
        attachment.merge(context, writer);
        helper.addAttachment("webinar.mht", new ByteArrayResource(writer.toString().getBytes()));

        javaMailSender.send(helper.getMimeMessage());

        webinar.setSentTCNotification(true);
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, mongoOperations);

        return "webinar";
    }

    @GetMapping("/notifyLuxmarketing")
    public String notifyLuxtown(@RequestParam(value = "id") String id, Model model) throws MessagingException, IOException {

        Query searchWebinarQuery = new Query(Criteria.where("id").is(id));
        Webinar webinar = mongoOperations.findOne(searchWebinarQuery, Webinar.class);

        Query searchUserQuery = new Query(Criteria.where("id").is(webinar.getUserId()));
        User user  = mongoOperations.findOne(searchUserQuery, User.class);

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(LUXOFT_AGILE_GMAIL_COM);
        helper.setCc(user.getLuxMail());
        helper.setTo(VMOSKALENKO_LUXOFT_COM);
        helper.setSubject(EMAIL_SUBJECT);

        VelocityContext context = new VelocityContext();
        Template body = velocityEngine.getTemplate("velocity/luxtownbody.ve", "UTF-16");
        context.put("link", String.format(BLOG_VIEW_LINK, webinar.getBlogPostCode()));
        context.put("signature", user.getSignature());
        StringWriter writer = new StringWriter();
        body.merge(context, writer);
        helper.setText(writer.toString(), false);
        javaMailSender.send(helper.getMimeMessage());

        String url = PROMOTE_URL + webinar.getBlogPostCode();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.getResponseCode();

        webinar.setSentForApproval(true);
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, mongoOperations);

        return "webinar";
    }

}
