package com.conygre.spring.boot.services;

import com.conygre.spring.boot.entities.CompactDisc;

public interface CompactDiscService {
	Iterable<CompactDisc> getCatalog();
	
	CompactDisc getCompactDiscById(int id);
	
	CompactDisc addNewCompactDisc(CompactDisc disc);
		
	void deleteCompactDisc(int id);
	
	void deleteCompactDisc(CompactDisc disc);
	
	CompactDisc updateCompactDisc(CompactDisc disc);
}