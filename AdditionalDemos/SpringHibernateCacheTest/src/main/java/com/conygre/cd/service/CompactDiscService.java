package com.conygre.cd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;

import com.conygre.training.entities.CompactDisc;
import com.googlecode.ehcache.annotations.Cacheable;

public class CompactDiscService {
	private EntityManagerFactory factory;
	
	public CompactDiscService()
	{
		factory = Persistence.createEntityManagerFactory("conygre");
		
	}

	@Cacheable(cacheName="testCache")
	public void getCompactDiscs() {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// get the Hibernate session object
		Session session = (Session) em.getDelegate();
		Statistics statistics= session.getSessionFactory().getStatistics();
		statistics.setStatisticsEnabled(true);
		
		Query q = session.createQuery("from CompactDisc");//.setCacheable(true);
		
		//Query allCDs= em.createQuery("from CompactDisc");
		List<CompactDisc> cdsA = q.list();
		
		for (CompactDisc compactDisc : cdsA) {
			System.out.println(compactDisc.getArtist());
		}
		
		CompactDisc cd = em.find(CompactDisc.class, 16);
		System.out.println(cd.getTitle());
		
		CompactDisc cd2 = em.find(CompactDisc.class, 16);
		
		
		
		tx.commit();
		session.close();
		System.out.println(cd.getTitle());
		System.out.println("There were "+ statistics.getSecondLevelCacheHitCount() +
				" hits on the cache and "+ statistics.getSecondLevelCacheMissCount() + " misses");

	}
	
	

}
