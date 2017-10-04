package com.conygre.training.remoting.http;

import java.util.Collection;

import com.conygre.training.entities.CompactDisc;

public interface CompactDiscService {
	
	CompactDisc getCompactDiscByTitle(String title);
	Collection<CompactDisc> getAllDiscs();
 
}
