package com.sujay.journalApplication.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI mySwaggerConfig() {
        return new OpenAPI()
            //Basic info shown at the top of Swagger UI (title, description)
                .info(new Info()
                        .title("Journal Application APIs")
                        .description("A secure and user-friendly RESTful API service for managing personal journal entries. "
                                + "Users can sign up, log in, create, update, delete, and view their journal entries with real-time weather integration. "
                                + "This application follows modular architecture using Spring Boot, JWT-based authentication, and MongoDB for data persistence. "
                                + "\n\n Developed and documented by Sujay S.")
                )

            // Define available servers (environments), Example: localhost (dev) and another port (prod/test)
            .servers(Arrays.asList(new Server().url("http://localhost:8080").description("dev"),
                    new Server().url("http://localhost:8081").description("prod")))

            // Add JWT Bearer Authentication support in Swagger UI, This shows an “Authorize” button to input the JWT token
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

            // Define security scheme details (type, name, where it’s used, etc.)
            .components(new Components().addSecuritySchemes(
                    "bearerAuth", new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)          // HTTP Type
                            .scheme("bearer")                        // Bearer token scheme
                            .bearerFormat("JWT")                     // JWT Format
                            .in(SecurityScheme.In.HEADER)            // Token sent in header
                            .name("Authorization")                   // Header name
            ));
    }
}