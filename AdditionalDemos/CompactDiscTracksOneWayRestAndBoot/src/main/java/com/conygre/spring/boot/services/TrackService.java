package com.conygre.spring.boot.services;

import com.conygre.spring.boot.entities.Track;

import java.util.Collection;

public interface TrackService {
    Collection<Track> getTracksByCdId(int cdId);
}
