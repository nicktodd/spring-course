package com.conygre.test.pets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Nick Todd on 03/04/2017.
 */
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jbBeans.xml");
        Person p = context.getBean(Person.class, "person");
        p.getPet().stroke();
    }
}
