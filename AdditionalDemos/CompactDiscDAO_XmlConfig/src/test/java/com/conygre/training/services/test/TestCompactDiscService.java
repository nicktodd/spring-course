package com.conygre.training.services.test;


import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.services.CompactDiscService;

public class TestCompactDiscService {

	private CompactDiscService service;
	
	@Before
	public void setUp() throws Exception {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		service = context.getBean("compactDiscService", CompactDiscService.class);
		
	}

	@Test
	public void testCanGetAllCds() {
		Collection<CompactDisc> allCds = service.getCatalog();
		assertTrue(allCds.size() > 1);	
		for (CompactDisc compactDisc : allCds) {
			System.out.println(compactDisc.getTitle());
		}
	}
	
}
