package com.conygre.spring.components;

import org.springframework.stereotype.Component;

@Component
public class DieselEngine implements Engine{

	private double engineSize = 3;
	
	@Override
	public double getEngineSize() {
		// TODO Auto-generated method stub
		return engineSize;
	}

	@Override
	public void setEngineSize(double engineSize) {
		// TODO Auto-generated method stub
		this.engineSize = engineSize;
	}

}
