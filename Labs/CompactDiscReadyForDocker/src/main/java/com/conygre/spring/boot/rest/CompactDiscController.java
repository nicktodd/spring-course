package com.conygre.spring.boot.rest;

import com.conygre.spring.boot.services.CompactDiscService;
import com.conygre.spring.boot.entities.CompactDisc;
import io.swagger.annotations.ApiOperation;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compactdiscs")
@CrossOrigin // allows requests from all domains
public class CompactDiscController {

	// private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CompactDiscService service;

	@ApiOperation(value = "findAll", nickname = "findAll")
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<CompactDisc> findAll() {
		// logger.info("managed to call a Get request for findAll");

		return service.getCatalog();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CompactDisc getCdById(@PathVariable("id") int id) {
		return service.getCompactDiscById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteCd(@PathVariable("id") int id) {
		service.deleteCompactDisc(id);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteCd(@RequestBody CompactDisc disc) {
		service.deleteCompactDisc(disc);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addCd(@RequestBody CompactDisc disc) {
		service.addNewCompactDisc(disc);
	}

}
