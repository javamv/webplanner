package com.autowebinar.core.data;

/**
 * Created by VMoskalenko on 10.01.2017.
 */
public class ConstantVariables {
    public static final String LUXOFT_AGILE_GMAIL_COM = "luxoft.agile@gmail.com";
    public static final String VMOSKALENKO_LUXOFT_COM = "vmoskalenko@luxoft.com";
    public static final String EMAIL_SUBJECT = "Promote a webinar from Luxoft Agile Practice";
    public static final String GOTO_LINK_TC = "https://attendee.gotowebinar.com/register/%1s?src=tcmail";
    public static final String GOTO_LINK_LT = "https://attendee.gotowebinar.com/register/%1s?source=luxtown";
    public static final String BLOG_LINK = "https://sentinel2.luxoft.com/sen/wiki/pages/viewpage.action?pageId=%1s";
    public static final String PROMOTE_URL = "https://sentinel2.luxoft.com/sen/wiki/promote-news/sendforapproval.action?pageId=";
    public static final String SENTINEL_REST_API_URL = "https://sentinel2.luxoft.com/sen/wiki/rest/api/content/";
    public static final String AUTH = "vmoskalenko:77Poland%0";

    public static String createGotoLink(Webinar webinar) {
        return String.format(ConstantVariables.GOTO_LINK_TC, webinar.getGotoLink());
    }

    public static String createGotoLinkLT(Webinar webinar) {
        return String.format(ConstantVariables.GOTO_LINK_LT, webinar.getGotoLink());
    }

    public static String createLuxtownLink(Webinar webinar) {
        return String.format(ConstantVariables.BLOG_LINK, webinar.getBlogPostCode());
    }
}
