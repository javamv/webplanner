package com.autowebinar.core.gotowebinar;

import com.citrix.gotocorelib.api.AuthenticationApi;
import com.citrix.gotocorelib.api.common.ApiException;
import com.citrix.gotocorelib.api.model.LoginResponse;
import com.citrix.gotowebinar.api.WebinarsApi;
import com.citrix.gotowebinar.api.model.CreatedWebinar;
import com.citrix.gotowebinar.api.model.DateTimeRange;
import com.citrix.gotowebinar.api.model.UpcomingWebinar;
import com.citrix.gotowebinar.api.model.WebinarReqCreate;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by VMoskalenko on 06.01.2017.
 * Session Bean to connect to goto webinar
 */
@Configuration
public class SpringGotoConfig {

    @Bean
    public GotoSession webinar() throws ApiException {
        String userName = "AgileEducation@Luxoft.com";
        String userPassword = "luxoft123";
        String consumerKey = "GrGhi9bl0ksWeHtZGIWOqVTh4PAQDBGA";
        AuthenticationApi authApi = new AuthenticationApi();
        LoginResponse response = authApi.directLogin(userName, userPassword, consumerKey, "password");
        String accessToken = response.getAccessToken();
        Long organizerKey = Long.parseLong(response.getOrganizerKey());
        return new GotoSession(accessToken, organizerKey, new WebinarsApi());
    }

}
