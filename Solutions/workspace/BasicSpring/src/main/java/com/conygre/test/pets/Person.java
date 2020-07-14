package com.conygre.test.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nick Todd on 03/04/2017.
 */
@Component
public class Person {
    private int age;
    private String name;

    @Autowired
    private Pet pet;

    public Person(Pet p) {
        this.pet = p;
    }
    public Person(){}


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
