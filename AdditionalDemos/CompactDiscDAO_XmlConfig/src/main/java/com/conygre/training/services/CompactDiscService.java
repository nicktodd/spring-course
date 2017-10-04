package com.conygre.training.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.conygre.training.dao.CompactDiscDAO;
import com.conygre.training.entities.CompactDisc;

@Transactional (propagation = Propagation.REQUIRED)
public class CompactDiscService {
	
	private CompactDiscDAO dao;
	
	@Required
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
}
