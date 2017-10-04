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



@Repository
public class SpringJPACompactDiscDAO implements CompactDiscDAO{

	
}
