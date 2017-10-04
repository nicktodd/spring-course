package com.conygre.spring.data.repos;

import com.conygre.training.entities.CompactDisc;
import org.springframework.data.repository.CrudRepository;

public interface  CompactDiscRepository extends CrudRepository<CompactDisc, Integer> {
	public Iterable<CompactDisc> findByArtist(String artist);
}
