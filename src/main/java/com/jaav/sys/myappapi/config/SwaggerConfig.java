package com.jaav.sys.myappapi.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Poder revisar la documentación de los servicios REST desde:
 * http://[host]:[port]/swagger-ui.html
 * http://[host]:[port]/v2/api-docs
 * @author JAAV
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Autowired
    ApplicationProperties applicationProperties;


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex("/*.*"))
                .paths(PathSelectors.regex("/api.*"))

                .build()
                .apiInfo(metaData());
    }


    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                applicationProperties.getTitle(),
                applicationProperties.getDescription(),
                applicationProperties.getVersion(),
                "Terminos del servicio",
                new Contact(applicationProperties.getContactName(),
                        applicationProperties.getContactUrl(),
                        applicationProperties.getContactEmail()),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",  Collections.emptyList());
        return apiInfo;
    }
}