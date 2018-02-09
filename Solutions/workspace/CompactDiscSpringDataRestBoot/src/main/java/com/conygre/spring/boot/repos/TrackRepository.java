package com.conygre.spring.boot.repos;

import com.conygre.training.entities.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository  extends CrudRepository<Track, Integer>{
}
