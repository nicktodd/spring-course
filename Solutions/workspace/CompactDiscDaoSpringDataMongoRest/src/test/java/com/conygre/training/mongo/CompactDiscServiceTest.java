package com.conygre.training.mongo;

import com.conygre.training.MongoJavaConfig;
import com.conygre.training.entities.CompactDisc;
import com.conygre.training.service.CompactDiscService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoJavaConfig.class, loader = AnnotationConfigContextLoader.class)
public class CompactDiscServiceTest {

    @Autowired
    private CompactDiscService service;

    @Autowired
    private MongoTemplate mongoTemplate;



    @Test
    @Ignore
    public void getAllCDsThroughServiceLayerAndRetrieve() {
        service.addToCatalog(new CompactDisc("Risque", "Chic", 12.99));
        // get the first item from the iterator
        CompactDisc disc = service.getCatalog().iterator().next();
        assertEquals("Chic", disc.getArtist());
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
