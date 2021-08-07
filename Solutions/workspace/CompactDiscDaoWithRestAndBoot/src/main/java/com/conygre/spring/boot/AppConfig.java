package com.conygre.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;



@SpringBootApplication
@Import(SwaggerConfig.class)
@ComponentScan // required in order for tests to pick up @Components
public class AppConfig {
	    public static void main(String[] args) {
	        SpringApplication.run(AppConfig.class, args);
	    }
	    

}
