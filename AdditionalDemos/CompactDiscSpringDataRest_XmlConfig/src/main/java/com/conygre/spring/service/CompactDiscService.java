package com.conygre.spring.service;

import com.conygre.spring.data.repos.CompactDiscRepository;
import com.conygre.training.entities.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CompactDiscService {

	@Autowired
	private CompactDiscRepository repository;

	
	public Iterable<CompactDisc> getCds() {
		return repository.findAll();
	}
	
	public Iterable<CompactDisc> getCDsByArtist(String artist) {
		return repository.findByArtist(artist);
	}
	

	
}
