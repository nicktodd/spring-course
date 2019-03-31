package com.conygre.training.data;

import com.conygre.training.entities.CompactDisc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CompactDiscRepository extends MongoRepository<CompactDisc, ObjectId> {

	public List<CompactDisc> findByTitle(String title);

	@Query("{'artist': ?0}")
	public List<CompactDisc> customFindByArtist(String s);

}
