package com.conygre.rest;

import com.conygre.spring.service.CompactDiscService;
import com.conygre.training.entities.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value="/compactdiscs")
public class CompactDiscController {

	private CompactDiscService service;

	@Autowired
	public void setService(CompactDiscService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public Collection<CompactDisc> getCompactDiscs() {
		Iterable<CompactDisc> iter = service.getCds();
		List<CompactDisc> discList = new ArrayList<CompactDisc>();
		iter.forEach(d -> discList.add(d));
		return discList;

	}
	

	
	

}
