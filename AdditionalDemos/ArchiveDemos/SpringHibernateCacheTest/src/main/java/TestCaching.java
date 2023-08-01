import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.stat.Statistics;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.cd.service.CompactDiscService;
import com.conygre.training.entities.CompactDisc;


public class TestCaching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CompactDiscService service = (CompactDiscService)context.getBean("service");
		service.getCompactDiscs();
		service.getCompactDiscs();
	}
}
