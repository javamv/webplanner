package com.autowebinar.core.data;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by VMoskalenko on 05.01.2017.
 */
public class Webinar {

    @Id
    private String id;

    private String topicEng;
    private String topicRus;
    private String descriptionEng;
    private String descriptionRus;
    private Date startDate;
    private Date endDate;
    private String gotoLink;
    private Boolean scheduled=false;
    private Boolean posted=false;
    private Boolean sentForApproval=false;
    private Boolean sentTCNotification =false;
    private String blogPostCode;
    private String userId;

    public Webinar(String topicEng, String topicRus, String descriptionEng, String descriptionRus) {
        this.topicEng = topicEng;
        this.topicRus = topicRus;
        this.descriptionEng = descriptionEng;
        this.descriptionRus = descriptionRus;
    }

    public Webinar(String topicEng, String descriptionEng, String userId) {
        this.topicEng = topicEng;
        this.descriptionEng = descriptionEng;
        this.userId = userId;
    }

    public Webinar() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBlogPostCode() {
        return blogPostCode;
    }

    public void setBlogPostCode(String blogPostCode) {
        this.blogPostCode = blogPostCode;
    }

    public Boolean getSentTCNotification() {
        return sentTCNotification;
    }

    public void setSentTCNotification(Boolean sentTCNotification) {
        this.sentTCNotification = sentTCNotification;
    }

    public Boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
    }

    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }

    public Boolean getSentForApproval() {
        return sentForApproval;
    }

    public void setSentForApproval(Boolean sentForApproval) {
        this.sentForApproval = sentForApproval;
    }

    public String getId() {
        return id;
    }

    public String getTopicEng() {
        return topicEng;
    }

    public String getTopicRus() {
        return topicRus;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public String getDescriptionRus() {
        return descriptionRus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getGotoLink() {
        return gotoLink;
    }

    public void setGotoLink(String gotoLink) {
        this.gotoLink = gotoLink;
    }

    @Override
    public String toString() {
        return "Webinar{" +
                "id='" + id + '\'' +
                ", topicEng='" + topicEng + '\'' +
                ", topicRus='" + topicRus + '\'' +
                ", descriptionEng='" + descriptionEng + '\'' +
                ", descriptionRus='" + descriptionRus + '\'' +
                '}';
    }
}
