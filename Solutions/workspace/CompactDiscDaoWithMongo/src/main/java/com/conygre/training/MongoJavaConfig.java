package com.conygre.training;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@ComponentScan
public class MongoJavaConfig extends AbstractMongoConfiguration{

    protected String getDatabaseName() {
        return "mydatabase";
    }

    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }
}
