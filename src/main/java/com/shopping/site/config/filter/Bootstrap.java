package com.shopping.site.config.filter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bootstrap {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
