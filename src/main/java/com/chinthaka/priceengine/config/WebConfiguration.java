package com.chinthaka.priceengine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-23
 */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //Disabling CORS only for frontend testing purposes
        registry.addMapping("/**").allowedMethods("*");
    }
}
