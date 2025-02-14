package com.disenofloral.backend.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica a todas las rutas que empiecen con /api/
                .allowedOrigins("http://localhost:3000") // Permite solicitudes desde el origen localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite estos métodos HTTP
                .allowedHeaders("*"); // Permite cualquier tipo de cabecera
    }
}
