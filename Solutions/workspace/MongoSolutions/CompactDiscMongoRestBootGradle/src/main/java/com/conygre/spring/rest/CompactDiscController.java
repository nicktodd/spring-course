package com.conygre.spring.rest;

import com.conygre.spring.service.CompactDiscService;

import java.util.Optional;

import com.conygre.spring.entities.CompactDisc;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compactdiscs")
@CrossOrigin // allows requests from all domains
public class CompactDiscController {

	// private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CompactDiscService service;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<CompactDisc> findAll() {
		// logger.info("managed to call a Get request for findAll");
		return service.getCatalog();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Optional<CompactDisc> getCdById(@PathVariable("id") String id) {
		return service.getCompactDiscById(new ObjectId(id));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteCd(@PathVariable("id") String id) {
		service.deleteCompactDisc(new ObjectId(""+id));
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteCd(@RequestBody CompactDisc disc) {
		service.deleteCompactDisc(disc);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addCd(@RequestBody CompactDisc disc) {
		service.addToCatalog(disc);
	}

}
