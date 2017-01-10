package com.autowebinar.core.app;

import com.autowebinar.core.data.SpringMongoConfig;
import com.autowebinar.core.email.SpringEmailConfig;
import com.autowebinar.core.gotowebinar.SpringGotoConfig;
import com.autowebinar.core.web.GotoController;
import com.autowebinar.core.web.SendEmailController;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by VMoskalenko on 07.01.2017.
 */
public class SendMail {
    private static String USER_NAME = "luxoft.agile";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "luxoftagile"; // GMail password
    private static String RECIPIENT = "vmoskalenko@luxoft.com";

    public static void main(String[] args) throws MessagingException {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringEmailConfig.class, SendEmailController.class);
        JavaMailSender javaMailSender = (JavaMailSender) ctx.getBean("javaMailSender");


        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("luxoft.agile@gmail.com");
        helper.setTo(RECIPIENT);
        helper.setSubject("Test message");
        helper.setText("Привет! В приложении вы найдете шаблон для нового вебинара");

        VelocityEngine ve = new VelocityEngine();
        ve.addProperty("resource.loader", "class");
        ve.addProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();
        /*  next, get the Template  */
        Template t = ve.getTemplate("velocity/tctemplate.mht");
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("description", "Test");
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        /* show the World */

        //Resource file = new ClassPathResource("velocity/tctemplate.mht");
        helper.addAttachment("webinar.mht", new ByteArrayResource(writer.toString().getBytes()));
        javaMailSender.send(helper.getMimeMessage());


    }

}
