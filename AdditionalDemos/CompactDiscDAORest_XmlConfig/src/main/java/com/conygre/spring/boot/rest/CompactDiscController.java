package com.conygre.spring.boot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.conygre.training.services.CompactDiscService;
import com.conygre.training.entities.CompactDisc;



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
