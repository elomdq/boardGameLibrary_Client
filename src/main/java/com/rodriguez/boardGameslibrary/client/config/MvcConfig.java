package com.rodriguez.boardGameslibrary.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry
                .addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");

        registry
                .addResourceHandler(Location.COVER_PATH + "**")
                .addResourceLocations("file:///D:/Java/Documentos/temp/");

        registry
                .addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");

        registry
                .addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}
