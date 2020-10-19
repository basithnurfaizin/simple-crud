package com.nurfaizin.backend.controller.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class RestMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/api/**").excludePathPatterns(
                "/api/register",
                "api/login"
        );
    }
}
