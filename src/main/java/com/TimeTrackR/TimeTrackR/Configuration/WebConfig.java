package com.TimeTrackR.TimeTrackR.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://lobster-app-2ifzk.ondigitalocean.app/")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .exposedHeaders("Set-Cookie") // Specificera vilka headers som ska exponeras till klienten
                .allowCredentials(true); // Tillåt att cookies skickas med anropet
    }
}
