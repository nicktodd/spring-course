package com.conygre.spring.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conygre.spring.boot.repos.CompactDiscRepository;
import com.conygre.training.entities.CompactDisc;
@Service
public class CompactDiscServiceImpl implements CompactDiscService {
	
	@Autowired
	private CompactDiscRepository dao;
	
	/* (non-Javadoc)
	 * @see com.conygre.spring.boot.services.ICompactDiscService#getCatalog()
	 */
	public Iterable<CompactDisc> getCatalog() {
		return dao.findAll();
	}

	@Override
	public CompactDisc getCompactDiscById(int id) {
		return dao.findOne(id);
	}

	@Override
	public CompactDisc addNewCompactDisc(CompactDisc disc) {
		return dao.save(disc);
	}


	@Override
	public void deleteCompactDisc(int id) {
		dao.delete(id);
		
	}

	@Override
	public void deleteCompactDisc(CompactDisc disc) {
		deleteCompactDisc(disc.getId());
	}
}
