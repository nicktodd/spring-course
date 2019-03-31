package com.conygre.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Configuration
@Import(JpaConfiguration.class)
@ComponentScan(basePackages = "com.conygre.spring")
public class AppConfig {
		
}

