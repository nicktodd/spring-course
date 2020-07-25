package com.conygre.spring.dao;
import com.conygre.spring.entities.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class CompactDiscDAOMongo implements CompactDiscDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addCompactDisc(CompactDisc disc) {
        mongoTemplate.insert(disc);

    }

    @Override
    public CompactDisc getCompactDiscByTitle(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        CompactDisc disc = mongoTemplate.findOne(query, CompactDisc.class);
        return disc;
    }

    @Override
    public Collection<CompactDisc> getDiscsByArtist(String artist) {
        Query query = new Query();
        query.addCriteria(Criteria.where("artist").is(artist));
        List<CompactDisc> discs = mongoTemplate.find(query, CompactDisc.class);
        return discs;
    }

    @Override
    public Collection<CompactDisc> getAllDiscs() {
        return mongoTemplate.findAll(CompactDisc.class);
    }
}
