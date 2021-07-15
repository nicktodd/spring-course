package com.conygre.spring.boot.services;

import com.conygre.spring.boot.entities.Track;
import com.conygre.spring.boot.repos.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository repo;

    public Collection<Track> getTracksByCdId(int cdId) {
        return repo.findByCdId(cdId);
    }

    @Override
    public void addTrack(Track t) {
        repo.save(t);
    }

}
