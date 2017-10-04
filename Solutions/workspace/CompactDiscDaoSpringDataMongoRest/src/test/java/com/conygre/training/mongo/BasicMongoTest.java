package com.conygre.training.mongo;

import com.conygre.training.MongoJavaConfig;
import com.conygre.training.entities.CompactDisc;
import com.mongodb.BasicDBObject;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoJavaConfig.class)//, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class BasicMongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void canInsertSuccessfully() {
        CompactDisc disc1 = new CompactDisc("Abba Gold", "Abba", 12.99);
        CompactDisc disc2 = new CompactDisc("True", "Spandau Ballet", 12.99);
        CompactDisc disc3 = new CompactDisc("Romantic Favourites", "Richard Clayderman", 12.99);

        mongoTemplate.insert(disc1);
        mongoTemplate.insert(disc2);
        mongoTemplate.insert(disc3);

        List<CompactDisc> discs = mongoTemplate.findAll(CompactDisc.class);
        discs.forEach(disc -> System.out.println(disc.getTitle()));
        assertEquals(3,discs.size());

    }
   @After
    public void cleanUp() {
        for (String collectionName : mongoTemplate.getCollectionNames()) {
            if (!collectionName.startsWith("system.")) {
                mongoTemplate.getCollection(collectionName).remove(new BasicDBObject());
            }
        }
    }
}
