package com.autowebinar.core.app;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.mail.MessagingException;
import java.io.StringWriter;

/**
 * Created by VMoskalenko on 07.01.2017.
 */
public class Velocity {
    private static String USER_NAME = "luxoft.agile";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "luxoftagile"; // GMail password
    private static String RECIPIENT = "vmoskalenko@luxoft.com";

    public static void main(String[] args) throws MessagingException {
                /*  first, get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        ve.addProperty("resource.loader", "class");
        ve.addProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();
        /*  next, get the Template  */
        Template t = ve.getTemplate("velocity/tcbody.ve", "UTF-16");
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("name", "World");
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */
        System.out.println( writer.toString() );
    }

}
