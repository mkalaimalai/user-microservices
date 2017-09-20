package com.mkalaimalai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class UserMicroServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMicroServicesApplication.class, args);
    }
}

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.mkalaimalai.controller"))
                .paths(PathSelectors.regex("/api.*"))
                .build();

    }
}
