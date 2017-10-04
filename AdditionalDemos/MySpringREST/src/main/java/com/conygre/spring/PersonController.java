package com.conygre.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("people")
public class PersonController {


	private static Map<Integer,Person> people = new HashMap<Integer,Person>();

	static {
		people.put(1, new Person(1, "Homer", 40));
		people.put(2, new Person(2, "Marge", 35));
		people.put(3, new Person(3, "Bart", 11));
		people.put(4, new Person(4, "Lisa", 8));
		people.put(5, new Person(5, "Maggie", 2));
	}


	@RequestMapping(method = RequestMethod.GET)
	public Collection<Person> getPeople() {
		return people.values();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Person getPersonById(@PathVariable("id") int id) {
		return people.get(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void deletePersonById(@PathVariable("id") int id) {
		people.remove(id);
	}

	@RequestMapping(method=RequestMethod.POST,
			consumes="application/json")
	public void addPerson(@RequestBody Person p) {
		people.put(p.getId(), p);
	}

	@RequestMapping(method=RequestMethod.PUT,
			consumes="application/json")
	public void updatePerson(@RequestBody Person p) {
		if (people.containsKey(p.getId())) {
			people.put(p.getId(), p);
		}

	}


}

class Person {
	private Integer id;

	public Person(Integer id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public Person() {
	}
	
}
