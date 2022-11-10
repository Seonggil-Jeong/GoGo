package com.gogo.diet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi dietServiceAPI() {
        return GroupedOpenApi.builder()
                .group("DietService API - V1")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("GoGo - DIET-SERVICE API")
                        .description("GoGo DietService API 명세서").version("v0.0.1"));
    }
}
