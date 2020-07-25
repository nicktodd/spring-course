package com.conygre.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.conygre.spring.data")
public class CompactDiscMongoRestBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompactDiscMongoRestBootApplication.class, args);
	}

}
