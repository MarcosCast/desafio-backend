package com.cooperativa.desafiobackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://desafio-backend-production-bcb7.up.railway.app",
                        "https://desafio-frontend-livid.vercel.app",
                        "https://desafio-frontend-prf5w7olf-marcoscasts-projects.vercel.app",
                        "https://desafio-frontend-marcoscasts-projects.vercel.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
