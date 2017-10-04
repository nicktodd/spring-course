package com.conygre.spring.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // the bean name will be the class in camel case
public class Car {

	private String make="BMW";
	private String model="3 Series";
	
	@Autowired(required=true)
	private Engine petrolEngine;
	
	
	
	
	public Engine getPetrolEngine() {
		return petrolEngine;
	}
	public void setPetrolEngine(Engine engine) {
		this.petrolEngine = engine;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
