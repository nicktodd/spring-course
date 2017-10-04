package com.conygre.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.conygre.spring.service.CompactDiscService;
import com.conygre.spring.entities.CompactDisc;

@RestController

@RequestMapping("/api/compactdiscs")
public class CompactDiscController {

	 @Autowired
	  private  CompactDiscService service;
	
	  @RequestMapping(method = RequestMethod.GET)
	    Iterable<CompactDisc> findAll() {
	        return service.getCatalog();
	    }
	  
	  
	  @RequestMapping(method = RequestMethod.GET, value="/{id}")
	    CompactDisc getCdById(@PathVariable("id") int id) {
	        return service.getCompactDiscById( id);
	  }
}
