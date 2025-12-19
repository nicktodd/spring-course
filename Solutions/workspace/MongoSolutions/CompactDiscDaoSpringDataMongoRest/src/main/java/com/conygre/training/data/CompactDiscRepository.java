package com.conygre.training.data;

import com.conygre.training.entities.CompactDisc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompactDiscRepository extends MongoRepository<CompactDisc, ObjectId> {

}
