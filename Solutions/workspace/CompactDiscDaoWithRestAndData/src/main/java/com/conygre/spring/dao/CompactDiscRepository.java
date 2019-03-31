package com.conygre.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.conygre.spring.entities.CompactDisc;
@Repository
public interface CompactDiscRepository extends CrudRepository<CompactDisc, Integer> {

    Iterable<CompactDisc> findByTitle(String title);

}
