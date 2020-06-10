package com.jaav.sys.myappapi.config;

import java.util.Collections;

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
public class SwaggerConfig extends Docket{

    //@Autowired DocketConfigProperties docketConfigProperties;

    //@Value("${swagger.info.title}")
    private String title = "SPRING BOOT - MYAPP-DEMO";

    //@Value("${swagger.info.description}")
    private String description = "Spring Boot REST API - MYAPP-DEMO";

    /**
     *  Constructor por defecto.... inicializara con la app
     */
    public SwaggerConfig() {
        super(DocumentationType.SWAGGER_2);
        this
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(metaData());

    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                title,
                description,
                "1.0",
                "Terminos del servicio",
                new Contact("José Alfonso Arauco Villar - JAAV",
                        "http://localhost:8081",
                        "araucovillar@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",  Collections.emptyList());

//        apiInfo.getTitle();
//        apiInfo.getDescription();
//        apiInfo.getVersion();
//        apiInfo.getTermsOfServiceUrl();
//        apiInfo.getContact();
//        apiInfo.getLicense();
//        apiInfo.getLicenseUrl();

        return apiInfo;
    }
}