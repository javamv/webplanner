package com.autowebinar.core.security;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.REDIRECT_URI;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //Spring Security ignores request to static resources such as CSS or JS files.
                .ignoring()
                .antMatchers("/assets/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Configures form login
                .formLogin()
                .loginPage("/api/login")
                .loginProcessingUrl("/api/auth")
                .failureUrl("/api/login?error=bad_credentials")
                .usernameParameter("code")
                .passwordParameter("code")
                //Configures the logout function
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/api/login")
                //Configures url based authorization
                .and()
                .authorizeRequests()
                //Anyone can access the urls
                .antMatchers("/api/auth",
                            "/api/login"
                ).permitAll()
                //The rest of the our application is protected.
                .antMatchers("/**").hasRole("USER");
    }

    /**
     * Configures the authentication manager bean which processes authentication
     * requests.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService);
    }

    /**
     * This bean is load the user specific data when form login is used.
     */
    @Autowired
    private SecurityService securityService;
}