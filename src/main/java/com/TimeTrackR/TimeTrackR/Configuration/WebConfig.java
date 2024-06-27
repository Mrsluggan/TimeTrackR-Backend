package com.TimeTrackR.TimeTrackR.Configuration;

import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://lobster-app-2ifzk.ondigitalocean.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .exposedHeaders("Set-Cookie") 
                .allowCredentials(true); 
    }

}
