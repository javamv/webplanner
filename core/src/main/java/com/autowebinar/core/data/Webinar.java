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
    private String descriptionEng;
    private Date startDate;
    private Date endDate;
    private String language;
    private String targetAudience;
    private String gotoLink;
    private Boolean scheduled=false;
    private Boolean posted=false;
    private Boolean sentForApproval=false;
    private Boolean sentTCNotification =false;
    private String blogPostCode;
    private String userId;
    private String imageLink;

    public Webinar(String topicEng, String descriptionEng, String userId, String imageLink, String language, String targetAudience) {
        this.topicEng = topicEng;
        this.descriptionEng = descriptionEng;
        this.userId = userId;
        this.imageLink = imageLink;
        this.targetAudience = targetAudience;
        this.language = language;
    }

    public Webinar() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public String getDescriptionEng() {
        return descriptionEng;
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

    public boolean isCurrentUserCreator(User currentUser) {
        return userId.equals(currentUser.getId());
    }
}
