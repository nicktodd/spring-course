package com.conygre.training.mongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by nicktodd on 24/06/15.
 */
public class MongoAppUsingSpringConfig {



    public static void main(String[] args) {

        //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfiguration.class);
        MongoOperations mongoOps = context.getBean("mongoTemplate", MongoTemplate.class);


        mongoOps.dropCollection(Person.class);
        Person p = new Person("Fred", 34);
        Person p1 = new Person("Joe", 34);



        // Insert is used to initially store the object into the database.
        mongoOps.insert(p);
        mongoOps.insert(p1);


        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Fred"));
        List<Person> fredPeople = mongoOps.find(query, Person.class);


        // Find
        // Update
        mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
        if (p !=null)
            System.out.println(p.getName() + p.getAge());
        // Delete
        //mongoOps.remove(p);
        
        // Check that deletion worked
        mongoOps.findAll(Person.class).forEach(
                m -> System.out.println("The name is " + m.getName()));





    }
}
