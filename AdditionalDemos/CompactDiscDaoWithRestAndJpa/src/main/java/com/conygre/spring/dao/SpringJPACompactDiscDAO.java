package com.conygre.spring.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import com.conygre.spring.dao.CompactDiscDAO;
import com.conygre.spring.entities.CompactDisc;


@Transactional(propagation=Propagation.REQUIRED)
@Repository
public class SpringJPACompactDiscDAO implements CompactDiscDAO{

	// this is all you need in the DAO.
	// all tx and opening and closing is managed by Spring
	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void addCompactDisc(CompactDisc disc) {
		Query query = em.createQuery("from CompactDisc");
		List<CompactDisc> discs = query.getResultList(); 
		if (!discs.contains(disc))
			em.persist(disc);
	}

	public CompactDisc getCompactDiscByTitle(String title) {
		Query query  = em.createQuery("from CompactDisc cd where cd.title = :title");
		query.setParameter("title", title);
		// remember we are assuming only one album has any particular title
		// could have used getSingleResult() but this an exception if there is more than one result
		List<CompactDisc> result = query.getResultList();		
		return result.get(0);
	}

	public Collection<CompactDisc> getDiscsByArtist(String artist) {		
		Query query  = em.createQuery("from CompactDisc cd where cd.artist = :artist");
		query.setParameter("artist", artist);
		List<CompactDisc> result = query.getResultList();
		return result;
	}

	public Collection<CompactDisc> getAllDiscs() {
		Query query = em.createQuery("from CompactDisc");
		List<CompactDisc> discs = query.getResultList(); 
		return discs;
	}

	public CompactDisc getCompactDiscById(int id) {
		return em.find(CompactDisc.class, id);
	}
}
