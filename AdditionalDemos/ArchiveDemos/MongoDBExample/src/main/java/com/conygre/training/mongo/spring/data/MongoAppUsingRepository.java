package com.conygre.training.mongo.spring.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MongoAppUsingRepository {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MongoDataConfiguration.class);
        PersonService service = context.getBean("personService", PersonService.class);
        service.getPeople().forEach(p -> System.out.println(p.getName()));



    }
}
