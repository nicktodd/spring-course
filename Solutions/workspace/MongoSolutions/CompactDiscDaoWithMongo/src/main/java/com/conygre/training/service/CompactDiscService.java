package com.conygre.training.service;

import com.conygre.training.dao.CompactDiscDAO;
import com.conygre.training.entities.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
public class CompactDiscService {

    @Autowired
    private CompactDiscDAO dao;


    public void addToCatalog(CompactDisc disc) {
        dao.addCompactDisc(disc);
    }

    public Collection<CompactDisc> getCatalog() {
        return dao.getAllDiscs();
    }

}
