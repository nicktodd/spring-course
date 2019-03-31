package com.conygre.training.rest;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.services.CompactDiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
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
