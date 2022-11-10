package com.gogo.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.gogo.user.constants.UserConstants.AUTHORIZATION_TOKEN_KEY;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("UserService API")
                .pathsToMatch("/**")
                .addOpenApiCustomiser(buildSecurityOpenApi()) // JWT Setting Config
                .build();
    }

    public OpenApiCustomiser buildSecurityOpenApi() {
        return OpenApi -> OpenApi.addSecurityItem(new SecurityRequirement().addList("TOKEN"))
                .getComponents()
                .addSecuritySchemes("TOKEN", new SecurityScheme()
                        .name(AUTHORIZATION_TOKEN_KEY)
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT")
                        .scheme("bearer"));
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("GoGo - USER-SERVICE API")
                        .description("GoGo UserService API 명세서").version("v0.0.1"));
    }
}
