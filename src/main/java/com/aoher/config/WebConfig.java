package com.aoher.config;

import com.aoher.session.SpringSessionBackedSessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSessionBackedSessionRegistry sessionRegistry;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable()
                .sessionManagement()
                    .maximumSessions(2)
                        .sessionRegistry(sessionRegistry)
                        .maxSessionsPreventsLogin(false)
                        .and()
                    .and()
                .authorizeRequests()
                    .antMatchers("/").authenticated();
    }
}
