package com.autowebinar.core.gotowebinar;

import com.citrix.gotowebinar.api.WebinarsApi;
import com.citrix.gotowebinar.api.common.ApiException;
import com.citrix.gotowebinar.api.model.CreatedWebinar;
import com.citrix.gotowebinar.api.model.WebinarReqCreate;

/**
 * Created by VMoskalenko on 06.01.2017.
 */
public class GotoSession extends WebinarsApi {

    private String accessToken;
    private Long organizerKey;
    private WebinarsApi webinarsApi;

    public GotoSession(String accessToken, Long organizerKey, WebinarsApi webinarsApi) {
        this.accessToken = accessToken;
        this.organizerKey = organizerKey;
        this.webinarsApi = webinarsApi;
    }

    public CreatedWebinar createWebinar(WebinarReqCreate webinar) throws ApiException {
        return webinarsApi.createWebinar(accessToken, organizerKey, webinar);
    }

    public void cancelWebinar(Long webinarKey) throws ApiException {
        webinarsApi.cancelWebinar(accessToken, organizerKey, webinarKey, false);
    }
}
