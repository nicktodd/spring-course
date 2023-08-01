package com.conygre.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories
@EntityScan("com.conygre.training.entities")
public class AppConfig {
	    public static void main(String[] args) {

	    	SpringApplication.run(AppConfig.class, args);
		}
}
