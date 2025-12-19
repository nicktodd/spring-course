package com.conygre.training.service;

import com.conygre.training.data.CompactDiscRepository;
import com.conygre.training.entities.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompactDiscService {

    @Autowired
    private CompactDiscRepository dao;


    public void addToCatalog(CompactDisc disc) {
        dao.insert(disc);
    }

    public List<CompactDisc> getCatalog() {
        return dao.findAll();
    }

    public List<CompactDisc> findByArtist(String artist) {
        return dao.customFindByArtist(artist);
    }

    public List<CompactDisc> findByTitle(String title) {
        return dao.findByTitle(title);
    }


}
