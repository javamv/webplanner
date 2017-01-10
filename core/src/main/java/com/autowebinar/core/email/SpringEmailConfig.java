package com.autowebinar.core.email;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by VMoskalenko on 07.01.2017.
 * E-mail session
 */
@Configuration
public class SpringEmailConfig {

    private static String USER_NAME = "luxoft.agile";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "luxoftagile"; // GMail password

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.AUTH", "true");

        Session session = Session.getDefaultInstance(props);
        javaMailSender.setSession(session);
        javaMailSender.setPort(587);
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPassword(PASSWORD);
        javaMailSender.setUsername(USER_NAME);

        return javaMailSender;
    }

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine ve = new VelocityEngine();
        ve.addProperty("resource.loader", "class");
        ve.addProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();
        return ve;
    }

}
