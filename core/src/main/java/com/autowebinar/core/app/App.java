package com.autowebinar.core.app;

import java.text.ParseException;
import java.util.*;

import com.autowebinar.core.data.SpringMongoConfig;
import com.autowebinar.core.data.User;
import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.gotowebinar.GotoSession;
import com.autowebinar.core.gotowebinar.SpringGotoConfig;
import com.autowebinar.core.web.GotoController;
import com.citrix.gotowebinar.api.common.ApiException;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static java.util.Calendar.MAY;


/**
 * Created by VMoskalenko on 05.01.2017.
 */
public class App {

    public static void main(String[] args) throws ParseException, ApiException {

        // For XML
        //ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

        // For Annotation
        /*ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class, SpringGotoConfig.class, GotoController.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");*/

        //User user = new User("mkyong", "password123");

        // save
        //mongoOperation.save(user);

        // now user object got the created id.
        //System.out.println("1. user : " + user);

        // query to search user
        //Query searchUserQuery = new Query(Criteria.where("id").is("586e55f992abe07fe40aebe9"));

        // find the saved user again.
        //User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        //System.out.println("2. find - savedUser : " + savedUser);

        // update password
        //mongoOperation.updateFirst(searchUserQuery,
        //        Update.update("password", "new password"),User.class);

        // find the updated user object
        //User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);

        //BasicDBObject query = new BasicDBObject();
        //query.put("_id", new ObjectId("586e55f992abe07fe40aebe9"));

        //Webinar webinar = mongoOperation.findOne(searchUserQuery, Webinar.class);

        /*String id = "586e55f992abe07fe40aebe9";
        String date = "12-12-2017";
        String startDate = "12:00";
        String endDate = "13:00";*/

/*        System.out.println("3. updatedUser : " + ((GotoController) ctx.getBean("gotoController"))
                .createGotoWebinar(id, date, startDate, endDate));*/

        // delete
        //mongoOperation.remove(searchUserQuery, User.class);

        // List, it should be empty now.
        //List<User> listUser = mongoOperation.findAll(User.class);
        //System.out.println("4. Number of user = " + listUser.size());


        System.out.printf(Locale.ENGLISH, "https://attendee.gotowebinar.com/register/%1d?src=tcmail", 1234);

    }

}
