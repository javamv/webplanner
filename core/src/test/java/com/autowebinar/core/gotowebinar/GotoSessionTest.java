package com.autowebinar.core.gotowebinar;

import com.citrix.gotowebinar.api.common.ApiException;
import org.junit.Test;

import java.text.ParseException;

/**
 * Created by VMoskalenko on 06.01.2017.
 */
public class GotoSessionTest {

    @Test
    public void testConnection() throws ApiException, com.citrix.gotocorelib.api.common.ApiException, ParseException {
        SpringGotoConfig session = new SpringGotoConfig();
        //session.ScheduleWebinar();
    }

}
