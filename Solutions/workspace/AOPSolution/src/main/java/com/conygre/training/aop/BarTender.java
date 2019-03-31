package com.conygre.training.aop;

public class BarTender implements  IBarTender {
	
	
	public void makeHotCoffee() {
		System.out.println("Here is your coffee!");
	}
	
	public void serveWhiskey() {
		System.out.println("On the rocks sir");
	}
	
	public void pullPint(String type) {
		if (type.equalsIgnoreCase("guinness"))
			System.out.println("No shamrock on top - that's for tourists!");
		else if (type.equalsIgnoreCase("fosters"))
			System.out.println("Here is your fizz beer");
		else
			System.out.println("Here is your pint");
	}
	
	public void pullPint() {
		System.out.println("Here is your pint of our cheapest beer");
	}

}
