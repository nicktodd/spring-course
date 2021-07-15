package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TrackRepository extends JpaRepository<Track, Integer> {

    Collection<Track> findByCdId(int cdId);
}
