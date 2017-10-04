package com.conygre.training.services.test;

import java.util.Collection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.services.CompactDiscService;

public class MainTest {
	public static void main(String[] args) {
		CompactDiscService service;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		service = context.getBean("compactDiscService", CompactDiscService.class);
		
		service.addToCatalog(new CompactDisc("Can't Stand Still",12.99,"Lionel Richie", 10));
		Collection<CompactDisc> allCds = service.getCatalog();	
		
		//allCds.forEach(cd -> System.out.println(cd.getTitle()));
		
		for (CompactDisc compactDisc : allCds) {
			System.out.println(compactDisc.getTitle());
		}
		//CompactDisc disc = 
		//		new CompactDisc("Test Album", 11.99, "Pink Floyd", 20);

		//service.addToCatalog(disc);
	}
	
	
}
