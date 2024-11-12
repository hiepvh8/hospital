package com.example.hospital.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//http://localhost:8081/swagger-ui/index.html#/
/*@OpenAPIDefinition(info = @Info(
        title = "API AndroidMusicApp",
        description = "API Documentation for My Spring Boot Application",
        version = "1.0"
))*/
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Swagger eShop Solution")   // Title của API
                        .version("v1")                    // Phiên bản API
                        .description("This is the API documentation for eShop Solution.")) // Mô tả về API
                .components(new Components()
                        .addSecuritySchemes("Bearer", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"));
    }
}