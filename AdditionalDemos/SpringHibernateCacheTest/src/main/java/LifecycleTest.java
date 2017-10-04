import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.conygre.training.entities.CompactDisc;


public class LifecycleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygre");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		CompactDisc cd = em.find(CompactDisc.class, 12);
		
		//cd.setTitle("My new title");
		
		tx.commit();
		em.close();
		
		cd.setTitle("My really nice new title");
		
		EntityManager em2 = factory.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		tx2.begin();
		
		em2.merge(cd);
		
		
		
		tx2.commit();
		em2.close();
		
	}

}
