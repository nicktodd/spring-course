package com.conygre.training;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EnableMongoRepositories(basePackages = "com.conygre.training.data")
@Import(WebAppConfig.class)
public class MongoJavaConfig extends AbstractMongoConfiguration{

    protected String getDatabaseName() {
        return "mydatabase";
    }

    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }
}
