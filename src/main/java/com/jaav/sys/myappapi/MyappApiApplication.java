package com.jaav.sys.myappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages={"com.jaav.sys"})
//@EnableConfigurationProperties
public class MyappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApiApplication.class, args);
	}

}
