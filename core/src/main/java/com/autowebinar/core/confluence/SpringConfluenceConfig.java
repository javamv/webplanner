package com.autowebinar.core.confluence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by VMoskalenko on 06.01.2017.
 */
@Configuration
public class SpringConfluenceConfig {

    @Bean
    public ConfluenceRestSession confluenceRestSession() throws IOException {
        return new ConfluenceRestSession();
    }

}
