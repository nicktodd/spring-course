package com.conygre.test.pets;
import com.conygre.test.pets.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class PetConfigurer {

    @Bean
    public Person person(@Autowired Pet pet) {
        return new Person(pet);
    }
    @Bean
    public Pet pet() {
        return new Cat();
    }
}
