package com;

import com.conygre.test.pets.Cat;
import com.conygre.test.pets.Person;


public class PersonWithPetNoSpring {
    public static void main(String[] args) {
        Person p = new Person();
        Cat cat = new Cat();
        p.setPet(cat);

        p.getPet().feed();
    }
}
