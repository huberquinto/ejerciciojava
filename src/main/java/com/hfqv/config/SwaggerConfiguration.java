package com.hfqv.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Huber Quinto API Usuarios")
                .description("Crear usuarios")
                .version("1.0")
        );
    }

    @Bean
    public GroupedOpenApi api(){

        return GroupedOpenApi.builder()
                .group("hfqv-apis-publicas")
                .pathsToMatch("/**")
                .build();
    }


}
