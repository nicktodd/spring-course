package com.conygre.spring.java.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.conygre.spring.beans.Address;
import com.conygre.spring.beans.Person;
import com.conygre.spring.components.Car;
import com.conygre.spring.components.Engine;
import com.conygre.spring.components.PetrolEngine;

@Configuration
public class PersonConfigurer {

	@Bean
	public Person person() {
		return new Person(address());
	}
	@Bean
	public Address address() {
		return new Address();
	}
	
	@Bean
	public Car car() {
		Car c = new Car();
		c.setMake("Ford");
		c.setModel("Fiesta");
		c.setPetrolEngine(engine());
		return c;
	}
	
	@Bean
	public Engine engine() {
		return new PetrolEngine();
	}
	
}
