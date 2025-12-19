import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import com.conygre.training.entities.CompactDisc;


public class LifecycleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygrePersistentUnit");
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
