package com.conygre.training.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BarTenderAspect {
	
	@Pointcut("execution(* serveWhiskey(..))")
	public void spirits() {}
	
	@Pointcut ("execution(* pullPint(..))")
	public void pullingPints(){}
	
	
	@Pointcut ("execution(* makeHotCoffee(..))")
	public void hotDrinks(){}
	
	@Before("spirits()")
	public void addIce() {
		System.out.println("Add ice first");
	}
	
	@Around("hotDrinks()")
	public void warn(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Better boil the water");
		joinPoint.proceed();
		System.out.println("careful - it's hot!");
	}
	
	@After("pullingPints()")
	public void checkLines() {
		System.out.println("Just checking the lines");
	}
	
}
