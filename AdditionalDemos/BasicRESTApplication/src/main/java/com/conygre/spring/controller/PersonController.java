package com.conygre.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("people")
public class PersonController implements IPersonController {


	private static Map<Integer,Person> people = new HashMap<Integer,Person>();

	static {
		people.put(1, new Person(1, "Homer", 40));
		people.put(2, new Person(2, "Marge", 35));
		people.put(3, new Person(3, "Bart", 11));
		people.put(4, new Person(4, "Lisa", 8));
		people.put(5, new Person(5, "Maggie", 2));
	}


	/* (non-Javadoc)
	 * @see com.conygre.spring.controller.IPersonController#getPeople()
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Person> getPeople() {
		return people.values();
	}

	/* (non-Javadoc)
	 * @see com.conygre.spring.controller.IPersonController#getPersonById(int)
	 */
	@Override
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Person getPersonById(@PathVariable("id") int id) {
		return people.get(id);
	}



	@RequestMapping(value="/with404/{id}", method=RequestMethod.GET)
	public ResponseEntity<Person> getPersonByIdHandling404(@PathVariable("id") int id) {
		Person personToReturn = people.get(id);

		if (personToReturn ==null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Person>(personToReturn, HttpStatus.OK);
		}
	}

	/* (non-Javadoc)
	 * @see com.conygre.spring.controller.IPersonController#deletePersonById(int)
	 */
	@Override
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void deletePersonById(@PathVariable("id") int id) {
		people.remove(id);
	}

	/* (non-Javadoc)
	 * @see com.conygre.spring.controller.IPersonController#addPerson(com.conygre.spring.controller.Person)
	 */
	@Override
	@RequestMapping(method=RequestMethod.POST,
			consumes="application/json")
	public void addPerson(@RequestBody Person p) {
		people.put(p.getId(), p);
	}

	/* (non-Javadoc)
	 * @see com.conygre.spring.controller.IPersonController#updatePerson(com.conygre.spring.controller.Person)
	 */
	@Override
	@RequestMapping(method=RequestMethod.PUT,
			consumes="application/json")
	public void updatePerson(@RequestBody Person p) {
		if (people.containsKey(p.getId())) {
			people.put(p.getId(), p);
		}

	}


}

class Person implements Serializable{
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
