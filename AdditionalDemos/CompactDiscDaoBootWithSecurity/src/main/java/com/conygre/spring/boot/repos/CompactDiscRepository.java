package com.conygre.spring.boot.repos;

import org.springframework.data.repository.CrudRepository;
import com.conygre.training.entities.CompactDisc;

public interface  CompactDiscRepository extends CrudRepository<CompactDisc, Integer> {
	public Iterable<CompactDisc> findByArtist(String artist);
}
