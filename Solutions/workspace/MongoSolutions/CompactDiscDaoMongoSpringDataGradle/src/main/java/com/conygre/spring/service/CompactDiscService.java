package com.conygre.spring.service;



import com.conygre.spring.data.CompactDiscRepository;
import com.conygre.spring.entities.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

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

}
