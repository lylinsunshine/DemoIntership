package com.shopping.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.shopping.filter.CORSFilter;

@EnableWebSecurity
public class EasyCors extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class);
    }


}
