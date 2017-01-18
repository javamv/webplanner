package com.autowebinar.core.web;

import com.autowebinar.core.data.User;
import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.data.WebinarLog;
import com.autowebinar.core.utils.ModelUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation .Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import static com.autowebinar.core.data.ConstantVariables.*;

/**
 * Created by VMoskalenko on 09.01.2017.
 *
 */
@Controller
public class SendEmailController extends BasicWebController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.luxoft.ptc.list}")
    private String ptcListRecipient;

    @Value("${email.luxoft.luxtown}")
    private String luxtownRecipient;


    @GetMapping("/notifyTC")
    @ResponseBody
    public String notifyTC(@RequestParam(value = "id") String id, Model model) throws MessagingException {

        Webinar webinar = getWebinar(id);

        User sender  = getCurrentUser();
        User webinarCreator = sender;

        if (!webinar.isCurrentUserCreator(sender)) {
            webinarCreator = getUserById(webinar.getUserId());
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(LUXOFT_AGILE_GMAIL_COM);
        helper.setCc(getLuxEmails(sender, webinarCreator));
        helper.setTo(ptcListRecipient);
        helper.setSubject(EMAIL_SUBJECT);
        prepareEmailForTC(webinar, sender, webinarCreator, helper);

        javaMailSender.send(helper.getMimeMessage());

        webinar.setSentTCNotification(true);
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, sender);

        mongoOperations.save(new WebinarLog(webinar.getId(), new Date(), 4L));

        return "Email to PTC has been sent";
    }

    private String[] getLuxEmails(User sender, User webinarCreator) {
        Set<String> emailsSet = new HashSet<String>();
        emailsSet.add(sender.getLuxMail());
        emailsSet.add(webinarCreator.getLuxMail());
        return emailsSet.toArray(new String[emailsSet.size()]);
    }

    @GetMapping("/notifyLuxmarketing")
    @ResponseBody
    public String notifyLuxtown(@RequestParam(value = "id") String id, Model model) throws MessagingException, IOException {

        Webinar webinar = getWebinar(id);
        User user  = getCurrentUser();

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(LUXOFT_AGILE_GMAIL_COM);
        helper.setCc(user.getLuxMail());
        helper.setTo(luxtownRecipient);
        helper.setSubject(EMAIL_SUBJECT);

        prepareEmailForMarketing(webinar, user, helper);
        javaMailSender.send(helper.getMimeMessage());

        webinar.setSentForApproval(true);
        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, user);

        mongoOperations.save(new WebinarLog(webinar.getId(), new Date(), 5L));

        return "Email to marketing has been sent";
    }

    private void prepareEmailForTC(Webinar webinar, User sender, User webinarCreator, MimeMessageHelper helper) throws MessagingException {
        VelocityContext context = new VelocityContext();
        Template body = velocityEngine.getTemplate("velocity/tcbody.ve", "UTF-16");
        context.put("signature", sender.getSignature());
        StringWriter writer = new StringWriter();
        body.merge(context, writer);
        helper.setText(writer.toString(), false);

        /*  Attachement  */
        String gotoUrl = createGotoLinkTC(webinar);

        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        startTime.setTime(webinar.getStartDate());

        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        endTime.setTime(webinar.getEndDate());

        Template attachment = velocityEngine.getTemplate("velocity/webinar.doc", "UTF-8");
        context = new VelocityContext();
        context.put("topic", webinar.getTopicEng());
        context.put("date", String.format(Locale.ENGLISH, "%1$tB %1$te", webinar.getStartDate()));
        context.put("time", String.format(Locale.ENGLISH, "%1$tI p.m. - %2$tI p.m. (Moscow time zone)",
                startTime, endTime));
        context.put("language", webinar.getLanguage());
        context.put("description", webinar.getDescriptionEng());
        context.put("instructor", webinarCreator.getUsername());
        context.put("targetAudience", webinar.getTargetAudience());
        context.put("link", gotoUrl);
        context.put("email", webinarCreator.getLuxMail());
        writer = new StringWriter();
        attachment.merge(context, writer);
        helper.addAttachment("webinar.doc", new ByteArrayResource(writer.toString().getBytes()));
    }

    private void prepareEmailForMarketing(Webinar webinar, User user, MimeMessageHelper helper) throws MessagingException {
        VelocityContext context = new VelocityContext();
        Template body = velocityEngine.getTemplate("velocity/luxtownbody.ve", "UTF-16");
        context.put("link", String.format(BLOG_VIEW_LINK, webinar.getBlogPostCode()));
        context.put("signature", user.getSignature());
        StringWriter writer = new StringWriter();
        body.merge(context, writer);
        helper.setText(writer.toString(), false);
    }

}
