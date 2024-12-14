package com.stocksafe.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class UtilsBean {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
