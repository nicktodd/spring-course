package com.conygre.spring.data.repos;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.conygre.training.entities.CompactDisc;

public interface  CompactDiscRepository extends CrudRepository<CompactDisc, Integer> {
	public Iterable<CompactDisc> findByArtist(String artist);
}
