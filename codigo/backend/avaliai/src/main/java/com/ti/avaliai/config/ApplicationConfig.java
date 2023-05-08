package com.ti.avaliai.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {



    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(new Info().title("Minha Api").version("1.0.0").description("Descrição"));
    }



}
