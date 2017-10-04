package com.conygre.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conygre.spring.data.repos.CompactDiscRepository;
import com.conygre.spring.data.repos.CompactDiscRepositoryMongo;
import com.conygre.training.entities.CompactDisc;
import com.conygre.training.entities.CompactDiscMongo;
@Service
public class CompactDiscService {

	@Autowired
	private CompactDiscRepository repository;
	
	@Autowired
	private CompactDiscRepositoryMongo mongoRepository;
	
	
	public Iterable<CompactDisc> getCds() {
		return repository.findAll();
	}
	
	public Iterable<CompactDisc> getCDsByArtist(String artist) {
		return repository.findByArtist(artist);
	}
	
	public void addMongoCD(CompactDiscMongo disc) {
		mongoRepository.insert(disc);
	}
	
	public Iterable<CompactDiscMongo> getMongoCDs() {
		return mongoRepository.findAll();
	}
	
}
