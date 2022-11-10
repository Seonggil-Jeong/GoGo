package com.gogo.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userServiceAPI() {
        return GroupedOpenApi.builder()
                .group("UserService API - V1")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authAPI() {
        return GroupedOpenApi.builder()
                .group("UserService API")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi allAPI() {
        return GroupedOpenApi.builder()
                .group("All UserService API")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("GoGo - USER-SERVICE API")
                        .description("GoGo UserService API 명세서").version("v0.0.1"));
    }
}
