package com.conygre.spring.boot.repos;

import org.springframework.data.repository.CrudRepository;
import com.conygre.training.entities.CompactDisc;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface  CompactDiscRepository extends CrudRepository<CompactDisc, Integer> {
	public Iterable<CompactDisc> findByArtist(String artist);
}
