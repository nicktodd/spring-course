package com.conygre.spring.boot.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.conygre.spring.boot.services.CompactDiscService;
import com.conygre.training.entities.CompactDisc;

import java.security.Principal;


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
	  
	  @RequestMapping(method=RequestMethod.DELETE, value="/{id}") 
	  public void deleteCd(@PathVariable("id") int id) {
		  service.deleteCompactDisc(id);
	  }
	  
	  @RequestMapping(method=RequestMethod.DELETE)
	  public void deleteCd(@RequestBody CompactDisc disc) {
		  service.deleteCompactDisc(disc);
	  }
	  
}
