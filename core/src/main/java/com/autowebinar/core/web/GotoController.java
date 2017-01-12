package com.autowebinar.core.web;

import com.autowebinar.core.data.Webinar;
import com.autowebinar.core.data.WebinarLog;
import com.autowebinar.core.gotowebinar.GotoSession;
import com.autowebinar.core.utils.ModelUtils;
import com.citrix.gotowebinar.api.model.CreatedWebinar;
import com.citrix.gotowebinar.api.model.DateTimeRange;
import com.citrix.gotowebinar.api.model.WebinarReqCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by VMoskalenko on 06.01.2017.
 */
@Controller
public class GotoController {

    @Autowired
    private GotoSession gotoSession;

    @Autowired
    private MongoOperations mongoOperations;

    @PostMapping("/createGotoWebinar")
    public String createGotoWebinar(@RequestParam(value = "id") String id,
                                     @RequestParam(value = "date") String dateString,
                                     @RequestParam(value = "startTime") String startTimeString,
                                     @RequestParam(value = "endTime") String endTimeString,
                                    Model model)
            throws com.citrix.gotowebinar.api.common.ApiException, ParseException {

        List<DateTimeRange> times = new ArrayList<DateTimeRange>();
        DateTimeRange time = new DateTimeRange();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

        Date startDate = dateFormat.parse(dateString+"T"+startTimeString);
        Date endDate = dateFormat.parse(dateString+"T"+endTimeString);
        time.setStartTime(startDate);
        time.setEndTime(endDate);
        times.add(time);

        Query searchUserQuery = new Query(Criteria.where("id").is(id));
        Webinar webinar = mongoOperations.findOne(searchUserQuery, Webinar.class);

        WebinarReqCreate newGotoWebinar = new WebinarReqCreate();
        newGotoWebinar.setTimes(times);
        newGotoWebinar.setDescription(webinar.getDescriptionEng());
        newGotoWebinar.setSubject(webinar.getTopicEng());

        CreatedWebinar createdGotoWebinar = gotoSession.createWebinar(newGotoWebinar);

        webinar.setEndDate(endDate);
        webinar.setStartDate(startDate);
        webinar.setGotoLink(createdGotoWebinar.getWebinarKey());
        webinar.setScheduled(true);

        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, mongoOperations);

        mongoOperations.save(new WebinarLog(webinar.getId(), new Date(), 2L));

        return "webinar";
    }

    @GetMapping("/cancelGotoWebinar")
    public String cancelGotoWebinar(@RequestParam(value = "id") String id, Model model)
            throws com.citrix.gotowebinar.api.common.ApiException, ParseException {

        Query searchWebinarQuery = new Query(Criteria.where("id").is(id));
        Webinar webinar = mongoOperations.findOne(searchWebinarQuery, Webinar.class);

        gotoSession.cancelWebinar(new Long(webinar.getGotoLink()));

        webinar.setGotoLink(null);
        webinar.setScheduled(false);

        mongoOperations.save(webinar);

        ModelUtils.webinarToModel(model, webinar, mongoOperations);

        mongoOperations.save(new WebinarLog(webinar.getId(), new Date(), 3L));

        return "webinar";
    }

}