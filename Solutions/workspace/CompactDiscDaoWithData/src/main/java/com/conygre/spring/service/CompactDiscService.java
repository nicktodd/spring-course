package com.conygre.spring.service;


import com.conygre.spring.dao.CompactDiscRepository;
import com.conygre.spring.entities.CompactDisc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
@Service
@Transactional (propagation = Propagation.REQUIRED)
public class CompactDiscService {
	
	@Autowired	
	private CompactDiscRepository dao;
	
	
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	public void addToCatalog(CompactDisc disc) {
		dao.save(disc);
	}
	
	public Collection<CompactDisc> getCatalog() {
		return makeCollection(dao.findAll());
	}

	public CompactDisc getCompactDiscById(int id) {
		return dao.findOne(id);
	}
	
	private static Collection<CompactDisc> makeCollection(Iterable<CompactDisc> iter) {
	    Collection<CompactDisc> list = new ArrayList<>();
	    for (CompactDisc item : iter) {
	        list.add(item);
	    }
	    return list;
	}
}
