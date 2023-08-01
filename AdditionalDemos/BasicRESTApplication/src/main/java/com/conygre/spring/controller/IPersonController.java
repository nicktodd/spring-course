package com.conygre.spring.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IPersonController {

	Collection<Person> getPeople();

	Person getPersonById(int id);

	void deletePersonById(int id);

	void addPerson(Person p);

	void updatePerson(Person p);

}