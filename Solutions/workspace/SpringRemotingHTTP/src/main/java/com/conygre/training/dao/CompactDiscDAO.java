package com.conygre.training.dao;

import java.util.Collection;

import com.conygre.training.entities.CompactDisc;

public interface CompactDiscDAO {
	
	void addCompactDisc(CompactDisc disc);
	CompactDisc getCompactDiscByTitle(String title);
	Collection<CompactDisc> getDiscsByArtist(String artist);
	Collection<CompactDisc> getAllDiscs();
}
