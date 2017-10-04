package com.conygre.training.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclarePrecedence;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PilotAspect {

	@Pointcut("execution(* com.conygre.training.flying.Pilot.*(..))")
	public void lookOut() {}
	
	@Pointcut("execution(* takeOff(..))")
	public void takeOffChecks() {}
	
	@Pointcut("execution(* land(..))")
	public void landingChecks() {}
	


	
	@Before("landingChecks()")
	public void getPermission() {
		System.out.println("Can I land now?");
	}
	
	@Before("takeOffChecks() || landingChecks()")
	public void check() {
		System.out.println("Run through the checklist first!");
	}
	
	
	
	@AfterThrowing(pointcut="landingChecks()", throwing="ex")
	public void getOutQuick(NullPointerException ex) {
		System.out.println("Got a null pointer, so better get out");
	}
	
	@Around("lookOut()")
	public void look(ProceedingJoinPoint joinPoint) throws Throwable { 
		System.out.println("Have a good lookout");
		joinPoint.proceed();
		System.out.println("And lookout some more!");
	}
	
}
