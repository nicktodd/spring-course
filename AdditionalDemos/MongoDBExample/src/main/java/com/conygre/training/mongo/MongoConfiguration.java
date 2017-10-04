package com.conygre.training.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {
    @Override
    public String getDatabaseName() {
        return "mydatabase";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);

    }
}
