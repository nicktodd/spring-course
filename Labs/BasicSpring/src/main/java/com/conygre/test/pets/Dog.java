package com.conygre.test.pets;

import org.springframework.stereotype.Component;

/**
 * Created by Nick Todd on 03/04/2017.
 */

public class Dog implements Pet {
    public void feed() {
        System.out.println("feed dog");
    }
}
