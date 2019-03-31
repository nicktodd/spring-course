package com.conygre.training.controller;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.service.CompactDiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/compactdiscs")
public class CompactDiscController {

   @Autowired
    private CompactDiscService service;

    @RequestMapping(method= RequestMethod.GET)
    public Iterable<CompactDisc> getAllDiscs() {
        return service.getCatalog();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity addCd(@RequestBody CompactDisc disc) {
        service.addToCatalog(disc);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
