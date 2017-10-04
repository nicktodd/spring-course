package com.conygre.spring.data.repos;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.conygre.training.entities.CompactDiscMongo;
@Repository
public interface  CompactDiscRepositoryMongo extends MongoRepository<CompactDiscMongo, String> {
	public Iterable<CompactDiscMongo> findByArtist(String artist);
}
