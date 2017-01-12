package com.autowebinar.core.data;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by VMoskalenko on 12.01.2017.
 */
public class WebinarLog {

    @Id
    private String id;

    private String webinarId;
    private Date date;
    private Long activityType;

    public WebinarLog(String webinarId, Date date, Long activityType) {
        this.webinarId = webinarId;
        this.date = date;
        this.activityType = activityType;
    }

    public String getWebinarId() {
        return webinarId;
    }

    public void setWebinarId(String webinarId) {
        this.webinarId = webinarId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getActivityType() {
        return activityType;
    }

    public void setActivityType(Long activityType) {
        this.activityType = activityType;
    }
}
