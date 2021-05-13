package com.conygre.spring.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.conygre.spring.entities.CompactDisc;

public interface CompactDiscRepository extends JpaRepository<CompactDisc, Integer> {

	 @Query("SELECT p FROM CompactDisc p WHERE LOWER(p.title) = LOWER(:title)")
	 public List<CompactDisc> findByTitleButIgnoreTheCase(@Param("title") String title);
	
	Collection<CompactDisc> findByTitle(String title);
	
    Collection<CompactDisc> findByArtist(String artist);

}
