//CorsConfig (permission for Angular)
//Angular builds a single-page app (SPA). The browser loads one page once, and after that
// Angular changes what's on screen with JavaScript instead of loading a new page every time

//1. Angular runs in your browser as JavaScript localhost:4200
//2. To get data, that JavaScript calls your API.
//    Angular sends a request to localhost:8080/api/applications
//3. The browser treats those as two different addresses.
//localhost:4200 and localhost:8080 have different ports,so the browser considers them different origins.
//As a safety rule, it blocks a page from one origin calling another unless the other one allows it.


package com.example.jobtracker;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}

//Browser (localhost:4200, Angular)
//        │  "give me the applications"
//        ▼
//Spring Boot (localhost:8080)
//        │  checks: is 4200 allowed? → yes (CorsConfig)
//        ▼
//returns JSON → Angular updates the page without reloading