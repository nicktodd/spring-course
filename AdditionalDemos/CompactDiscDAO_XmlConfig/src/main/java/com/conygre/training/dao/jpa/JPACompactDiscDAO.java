package com.conygre.training.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.conygre.training.dao.CompactDiscDAO;
import com.conygre.training.entities.CompactDisc;

public class JPACompactDiscDAO implements CompactDiscDAO {

	private EntityManagerFactory factory;
	
	public JPACompactDiscDAO() {
		factory = Persistence.createEntityManagerFactory("conygre");
	}
	
	private EntityManager getEntityManager() {
		EntityManager em = factory.createEntityManager();
		return em;
	}

	
	public void addCompactDisc(CompactDisc disc) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		// not the best place to start at TX but nevermind for now!
		tx.begin();
		Query query = em.createQuery("from CompactDisc");
		List<CompactDisc> discs = query.getResultList(); 
		if (!discs.contains(disc))
			em.persist(disc);
		tx.commit();
		closeEntityManager(em);
	}

	private void closeEntityManager(EntityManager em) {
		// TODO Auto-generated method stub
		em.close();
	}

	public CompactDisc getCompactDiscByTitle(String title) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		// not the best place to start at TX but nevermind for now!
		tx.begin();
		Query query  = em.createQuery("from CompactDisc cd where cd.title = :title");
		query.setParameter("title", title);
		// remember we are assuming only one album has any particular title
		// could have used getSingleResult() but this an exception if there is more than one result
		List<CompactDisc> result = query.getResultList();
		
		tx.commit();
		closeEntityManager(em);
		
		return result.get(0);
	}

	public Collection<CompactDisc> getDiscsByArtist(String artist) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		// not the best place to start at TX but nevermind for now!
		tx.begin();
		Query query  = em.createQuery("from CompactDisc cd where cd.artist = :artist");
		query.setParameter("artist", artist);
		List<CompactDisc> result = query.getResultList();
		
		tx.commit();
		closeEntityManager(em);
		
		return result;
	}

	public Collection<CompactDisc> getAllDiscs() {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		// not the best place to start at TX but nevermind for now!
		tx.begin();
		Query query = em.createQuery("from CompactDisc");
		List<CompactDisc> discs = query.getResultList(); 
		tx.commit();
		closeEntityManager(em);
		return discs;
		
	}

}
