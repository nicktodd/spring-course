package com.conygre.spring.dao;


import com.conygre.spring.entities.CompactDisc;

import java.util.Collection;

public interface CompactDiscDAO {

    void addCompactDisc(CompactDisc disc);
    CompactDisc getCompactDiscByTitle(String title);
    Collection<CompactDisc> getDiscsByArtist(String artist);
    Collection<CompactDisc> getAllDiscs();
}