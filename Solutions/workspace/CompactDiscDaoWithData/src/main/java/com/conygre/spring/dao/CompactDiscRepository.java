package com.conygre.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.conygre.spring.entities.CompactDisc;

public interface CompactDiscRepository extends CrudRepository<CompactDisc, Integer> {

	 @Query("SELECT p FROM CompactDisc p WHERE LOWER(p.title) = LOWER(:title)")
	 public List<CompactDisc> find(@Param("title") String title);
	
	
	
    Iterable<CompactDisc> findByArtist(String artist);




}
