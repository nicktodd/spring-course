package com.conygre.spring.service;

import com.conygre.spring.dao.CompactDiscDAO;
import com.conygre.spring.entities.CompactDisc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
@Service
@Transactional (propagation = Propagation.REQUIRED)
public class CompactDiscService {
	
		
	private CompactDiscDAO dao;
	
	@Autowired
	public void setDao(CompactDiscDAO dao) {
		this.dao = dao;
	}
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	public void addToCatalog(CompactDisc disc) {
		if (!dao.getAllDiscs().contains(disc))
			dao.addCompactDisc(disc);
	}
	
	public Collection<CompactDisc> getCatalog() {
		return dao.getAllDiscs();
	}

	public CompactDisc getCompactDiscById(int id) {
		return dao.getCompactDiscById(id);
	}
}
