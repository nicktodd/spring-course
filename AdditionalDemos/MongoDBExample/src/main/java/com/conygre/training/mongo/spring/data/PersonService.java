package com.conygre.training.mongo.spring.data;

import com.conygre.training.mongo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    @Autowired
    private PersonRepository repo;

    public Iterable<Person> getPeople() {
        return repo.findAll();
    }
    public void addPerson(Person p) {
        repo.save(p);
    }
}
