package com.conygre.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpringDAOWithJdbc {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		CompactDiscSpringDAO dao = context.getBean("dao", CompactDiscSpringDAO.class);
		CompactDisc disc = dao.getCdById(14);
		System.out.println(disc.getArtist());
	}

}
