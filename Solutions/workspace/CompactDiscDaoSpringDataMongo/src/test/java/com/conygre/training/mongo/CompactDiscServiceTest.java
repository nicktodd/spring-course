package com.conygre.training.mongo;

import com.conygre.training.MongoJavaConfig;
import com.conygre.training.entities.CompactDisc;
import com.conygre.training.service.CompactDiscService;
import com.mongodb.BasicDBObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoJavaConfig.class, loader = AnnotationConfigContextLoader.class)
public class CompactDiscServiceTest {

    @Autowired
    private CompactDiscService service;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Before
    public void setup() {
        CompactDisc disc1 = new CompactDisc("Abba Gold", "Abba", 12.99);
        CompactDisc disc2 = new CompactDisc("True", "Spandau Ballet", 12.99);
        CompactDisc disc3 = new CompactDisc("Romantic Favourites", "Richard Clayderman", 12.99);
        service.addToCatalog(new CompactDisc("Risque", "Chic", 12.99));
        service.addToCatalog(disc1);
        service.addToCatalog(disc2);
        service.addToCatalog(disc3);
    }


    @Test
    public void getAllCDsThroughServiceLayerAndRetrieve() {


        List<CompactDisc> discs = service.getCatalog();
        assertEquals(4, discs.size());
    }

    @Test
    public void getByArtist() {
        List<CompactDisc> discs = service.findByArtist("Abba");
        assertEquals("Abba", discs.get(0).getArtist());
    }


    @Test
    public void getByTitle() {
        List<CompactDisc> discs = service.findByTitle("Abba Gold");
        assertEquals("Abba", discs.get(0).getArtist());
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
