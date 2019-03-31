package com.cisco.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public Person person() {
        Person p = new Person();
        p.setPet(dog());
        return p;
    }

    @Bean
    public Pet dog() {
        return new Dog();
    }


}
