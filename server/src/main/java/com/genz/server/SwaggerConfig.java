package com.genz.server;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author Nikos.Toulios
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = ServerApplication.class)
public class SwaggerConfig {
    @SuppressWarnings("unchecked")
    @Bean
    public Docket swagger2Configuration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("GenZ Rest API")
                        .description("This api is for GenZ application.")
                        .contact("toulios90@gmail.com")
                        .build())
                .select()
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.genz.server")))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .useDefaultResponseMessages(false);
    }
}
