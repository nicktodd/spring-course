package com.cisco.training;

import org.springframework.stereotype.Component;



public class Dog implements Pet{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void feed() {
        System.out.println("feed dog");
    }
}
