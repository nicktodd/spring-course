package com.conygre.spring.beans;

public class Chair implements Furniture {

	private String material;
	
	@Override
	public String getMaterial() {
		// TODO Auto-generated method stub
		return material;
	}

	@Override
	public void setMaterial(String material) {
		// TODO Auto-generated method stub
		this.material = material;
	}

}
