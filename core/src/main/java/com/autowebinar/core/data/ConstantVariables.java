package com.autowebinar.core.data;

/**
 * Created by VMoskalenko on 10.01.2017.
 */
public class ConstantVariables {
    public static final String LUXOFT_AGILE_GMAIL_COM = "luxoft.agile@gmail.com";
    public static final String VMOSKALENKO_LUXOFT_COM = "vmoskalenko@luxoft.com";
    public static final String EMAIL_SUBJECT = "Promote a webinar from Luxoft Agile Practice";
    public static final String GOTO_LINK = "https://attendee.gotowebinar.com/register/%1s%2s";
    public static final String GOTO_MANAGE_LINK = "https://global.gotowebinar.com/manageWebinar.tmpl?webinar=%1s";
    public static final String BLOG_VIEW_LINK = "https://sentinel2.luxoft.com/sen/wiki/pages/viewpage.action?pageId=%1s";
    public static final String BLOG_EDIT_LINK = "https://sentinel2.luxoft.com/sen/wiki/pages/editblogpost.action?pageId=%1s";
    public static final String PROMOTE_URL = "https://sentinel2.luxoft.com/sen/wiki/promote-news/sendforapproval.action?pageId=";
    public static final String SENTINEL_REST_API_URL = "https://sentinel2.luxoft.com/sen/wiki/rest/api/content/";

    public static String createGotoLinkTC(Webinar webinar) {
        return String.format(ConstantVariables.GOTO_LINK, webinar.getGotoLink(),"?src=tcmail");
    }

    public static String createGotoLinkLT(Webinar webinar) {
        return String.format(ConstantVariables.GOTO_LINK, webinar.getGotoLink(), "?source=luxtown");
    }

    public static String createGotoLink(Webinar webinar) {
        return String.format(ConstantVariables.GOTO_LINK, webinar.getGotoLink(), "");
    }


    public static String createManageLink(Webinar webinar) {
        return String.format(ConstantVariables.GOTO_MANAGE_LINK, webinar.getGotoLink());
    }

    public static String luxtownViewLink(Webinar webinar) {
        return String.format(ConstantVariables.BLOG_VIEW_LINK, webinar.getBlogPostCode());
    }

    public static String luxtownEditLink(Webinar webinar) {
        return String.format(ConstantVariables.BLOG_EDIT_LINK, webinar.getBlogPostCode());
    }
}
