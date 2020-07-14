package com.conygre.test.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nick Todd on 03/04/2017.
 */

public class Person {

    private Pet pet;

    public Person(Pet p) {
        this.pet = p;
    }
    public Person(){}




    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
