package com.conygre.spring.boot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories
@EntityScan("com.conygre.training.entities")
@EnableSwagger2
public class AppConfig {
	    public static void main(String[] args) {
	        SpringApplication.run(AppConfig.class, args);
	    }
	    
	    
	    
	    @Bean
	    public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("compactdiscs")
	                .apiInfo(apiInfo())
	                .select()
	                .paths(PathSelectors.any())
	                .build();
	    }
	     
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Album REST API with Swagger")
	                .description("This API allows you to interact with albums. It is a CRUD API")
	                //.termsOfServiceUrl("http://www.conygre.com")
	                .contact("Nick Todd")
	                //.license("Apache License Version 2.0")
	                //.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
	                //.version("2.0")
	                .build();
	    }
}
