package com.autowebinar.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * Created by vmoskalenko on 13.01.2017.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.autowebinar.core.data",
        "com.autowebinar.core.confluence",
        "com.autowebinar.core.email",
        "com.autowebinar.core.gotowebinar"
})
@PropertySource("classpath:application.properties")
public class AppConfiguration {
}
