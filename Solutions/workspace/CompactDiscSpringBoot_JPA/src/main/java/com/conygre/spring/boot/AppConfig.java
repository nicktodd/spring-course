package com.conygre.spring.boot;

import com.conygre.spring.boot.services.CompactDiscService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;



@SpringBootApplication
public class AppConfig {
	    public static void main(String[] args) {
	        var context = SpringApplication.run(AppConfig.class, args);
			context.getBean(CompactDiscService.class).getCatalog().forEach(disc -> System.out.println(disc.getTitle()));
		}
}
