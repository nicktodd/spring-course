package com.conygre.training.mongo.spring.data;

import com.conygre.training.mongo.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {

}
