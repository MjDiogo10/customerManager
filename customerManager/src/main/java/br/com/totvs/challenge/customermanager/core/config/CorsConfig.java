package br.com.totvs.challenge.customermanager.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // Permite todos os origins
                .allowedMethods("*")  // Permite todos os métodos
                .allowedHeaders("*")  // Permite todos os headers
                .exposedHeaders("*")  // Expõe todos os headers na resposta
                .allowCredentials(false);  // IMPORTANTE: não pode ser true
    }
}
