package com.conygre.training.flying;
public class PrivatePilot implements Pilot {
	
	public void land() {
		System.out.println("Land the plane");
		//throw new NullPointerException();
	}
	
	public void navigate() {
		System.out.println("Fly straight and level");
	}
	
	public void takeOff() {
		System.out.println("Take off");
	}
}
