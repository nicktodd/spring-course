package com.conygre.spring.service;



import com.conygre.spring.data.CompactDiscRepository;
import com.conygre.spring.entities.CompactDisc;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class CompactDiscService {

    @Autowired
    private CompactDiscRepository dao;


    public void addToCatalog(CompactDisc disc) {
        dao.insert(disc);
    }

    public Collection<CompactDisc> getCatalog() {
        return dao.findAll();
    }

    public Optional<CompactDisc> getCompactDiscById(ObjectId id) {
        return dao.findById(id);
    }

    public void deleteCompactDisc(ObjectId id) {
        dao.deleteById(id);
    }

    public void deleteCompactDisc(CompactDisc disc) {
        dao.delete(disc);
    }


}
