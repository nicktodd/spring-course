package com.conygre.spring.service;

import com.conygre.spring.dao.CompactDiscRepository;
import com.conygre.spring.entities.CompactDisc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CompactDiscService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CompactDiscRepository dao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addToCatalog(CompactDisc disc) {
		dao.save(disc);
	}

	public Collection<CompactDisc> getCatalog() {
		logger.info("just getting the catalog");
		return dao.findAll();
	}

	public Optional<CompactDisc> getCompactDiscById(int id) {
		return dao.findById(id);
	}


}
