package com.conygre.spring.boot.repos;

import org.springframework.data.repository.CrudRepository;
import com.conygre.training.entities.CompactDisc;
import org.springframework.data.repository.query.Param;

public interface  CompactDiscRepository extends CrudRepository<CompactDisc, Integer> {

	// The url for this would be http://localhost:8080/api/compactDiscs/search/findByArtist?artist=Coldplay
	// see https://spring.io/guides/gs/accessing-data-rest/
	public Iterable<CompactDisc> findByArtist(@Param("artist") String artist);
}
