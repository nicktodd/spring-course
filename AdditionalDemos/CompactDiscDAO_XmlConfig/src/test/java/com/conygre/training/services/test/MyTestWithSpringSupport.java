package com.conygre.training.services.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.services.CompactDiscService;
// need to add the Maven dependency on Spring - tests
@ContextConfiguration("classpath:beans.xml")
public class MyTestWithSpringSupport  
			extends AbstractJUnit4SpringContextTests {

	@Test
	public void testCanGetAllCds() {
		
		CompactDiscService service = applicationContext.getBean("compactDiscService", CompactDiscService.class);
		Collection<CompactDisc> allCds = service.getCatalog();
		assertTrue(allCds.size() > 1);	
		for (CompactDisc compactDisc : allCds) {
			System.out.println(compactDisc.getTitle());
		}
	}
	
	
	
	
}
