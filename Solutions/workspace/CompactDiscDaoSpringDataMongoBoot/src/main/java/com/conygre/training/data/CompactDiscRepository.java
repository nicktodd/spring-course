package com.conygre.training.data;

import com.conygre.training.entities.CompactDisc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface CompactDiscRepository extends MongoRepository<CompactDisc, ObjectId> {
    Collection<CompactDisc> findByTitle(String title);
}
