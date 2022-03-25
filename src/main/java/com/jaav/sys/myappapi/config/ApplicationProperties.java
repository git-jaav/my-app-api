package com.jaav.sys.myappapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationProperties {

    @Value("${swagger.info.title}")
    private String title;

    @Value("${swagger.info.description}")
    private String description;

    @Value("${swagger.info.version}")
    private String version;

    @Value("${swagger.info.contact.name}")
    private String contactName;

    @Value("${swagger.info.contact.url}")
    private String contactUrl;

    @Value("${swagger.info.contact.email}")
    private String contactEmail;

    @Value("${application.process.name}")
    private String processName;

}
