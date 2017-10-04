package com.conygre.training.dao.fake;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.conygre.training.dao.CompactDiscDAO;
import com.conygre.training.entities.CompactDisc;

public class FakeCompactDiscDAO implements CompactDiscDAO {
	
	private Set<CompactDisc> library;
		
	public Set<CompactDisc> getLibrary() {
		return library;
	}

	public void setLibrary(Set<CompactDisc> library) {
		this.library = library;
	}

	public void addCompactDisc(CompactDisc disc) {
		// TODO Auto-generated method stub
		library.add(disc);

	}

	public CompactDisc getCompactDiscByTitle(String title) {
		// Assumes only 1 CD ever has a particular title
		for (CompactDisc compactDisc : library) {
			if (compactDisc.getTitle().equals(title)) {
				return compactDisc;
			}
		}
		// will only return if there are no matches
		return null;
	}

	public Collection<CompactDisc> getDiscsByArtist(String artist) {
		Set<CompactDisc> returnDiscs = new HashSet<CompactDisc>();
		for (CompactDisc compactDisc : library) {
			if (compactDisc.getArtist().equals(artist)) {
				returnDiscs.add(compactDisc);
			}
		}
		return returnDiscs;	}

	public Collection<CompactDisc> getAllDiscs() {
		// TODO Auto-generated method stub
		return library;
	}

}
