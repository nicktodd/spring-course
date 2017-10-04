package com.jbinternational;

import org.springframework.stereotype.Component;

@Component("italianCar")
public class Car {

	private String make="Fiat";

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
	
	
	
}
