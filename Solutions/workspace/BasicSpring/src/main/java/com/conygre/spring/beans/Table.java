package com.conygre.spring.beans;

public class Table implements Furniture {
	
	private int numberOfLegs;
	private String material;
	public int getNumberOfLegs() {
		return numberOfLegs;
	}
	public void setNumberOfLegs(int numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}
	/* (non-Javadoc)
	 * @see com.conygre.spring.beans.Furniture#getMaterial()
	 */
	@Override
	public String getMaterial() {
		return material;
	}
	/* (non-Javadoc)
	 * @see com.conygre.spring.beans.Furniture#setMaterial(java.lang.String)
	 */
	@Override
	public void setMaterial(String material) {
		this.material = material;
	}
	
	

}
