package com.mkalaimalai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.reader.MetricReader;
import org.springframework.boot.actuate.metrics.reader.MetricRegistryMetricReader;
import org.springframework.boot.actuate.metrics.statsd.StatsdMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

@SpringBootApplication
@EnableCaching
public class UserMicroServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMicroServicesApplication.class, args);
    }

    @Value("${spring.application.name:application}.${random.value:0000}")
    private String prefix = "metrics";

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter() {
        return new StatsdMetricWriter(prefix, "localhost", 8125);
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

@Configuration
class EtagConfig {

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
}


