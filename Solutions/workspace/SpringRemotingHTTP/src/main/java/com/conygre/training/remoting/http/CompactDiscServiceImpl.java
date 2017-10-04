package com.conygre.training.remoting.http;

import java.util.Collection;

import com.conygre.training.dao.CompactDiscDAO;
import com.conygre.training.entities.CompactDisc;

public class CompactDiscServiceImpl implements CompactDiscService {

	private CompactDiscDAO compactDiscDAO;
	
	
	
	public CompactDiscDAO getCompactDiscDAO() {
		return compactDiscDAO;
	}

	public void setCompactDiscDAO(CompactDiscDAO compactDiscDAO) {
		this.compactDiscDAO = compactDiscDAO;
	}

	public CompactDisc getCompactDiscByTitle(String title) {
		// TODO Auto-generated method stub
		return compactDiscDAO.getCompactDiscByTitle(title);
	}

	public Collection<CompactDisc> getAllDiscs() {
		// TODO Auto-generated method stub
		return compactDiscDAO.getAllDiscs();
	}

}
