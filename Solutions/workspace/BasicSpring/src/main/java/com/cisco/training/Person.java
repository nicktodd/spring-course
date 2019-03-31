package com.cisco.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Person {


    private Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
