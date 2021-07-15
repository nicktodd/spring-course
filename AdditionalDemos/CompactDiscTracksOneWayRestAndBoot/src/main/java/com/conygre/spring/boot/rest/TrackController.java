package com.conygre.spring.boot.rest;

import com.conygre.spring.boot.entities.Track;
import com.conygre.spring.boot.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/tracks")
@CrossOrigin
public class TrackController {

    @Autowired
    private TrackService service;


    @GetMapping("/{cdId}")
    public Collection<Track> getTracksByCdId(@PathVariable("cdId") int cdId) {
        return service.getTracksByCdId(cdId);
    }

}
