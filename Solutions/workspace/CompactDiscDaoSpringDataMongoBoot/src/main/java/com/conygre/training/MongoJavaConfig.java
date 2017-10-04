package com.conygre.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableMongoRepositories(basePackages = "com.conygre.training.data")
public class MongoJavaConfig {

	public static void main(String[] args) {
		SpringApplication.run(MongoJavaConfig.class, args);
	}
}
