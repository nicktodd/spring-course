package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.CompactDisc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompactDiscRepository extends JpaRepository<CompactDisc, Integer> {

	public Iterable<CompactDisc> findByArtist(String artist);
}
