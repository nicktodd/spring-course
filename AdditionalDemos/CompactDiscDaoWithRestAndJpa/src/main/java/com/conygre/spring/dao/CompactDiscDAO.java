package com.conygre.spring.dao;

import java.util.Collection;

import com.conygre.spring.entities.CompactDisc;

public interface CompactDiscDAO {
	
	void addCompactDisc(CompactDisc disc);
	CompactDisc getCompactDiscByTitle(String title);
	Collection<CompactDisc> getDiscsByArtist(String artist);
	Collection<CompactDisc> getAllDiscs();
	CompactDisc getCompactDiscById(int id);
}
