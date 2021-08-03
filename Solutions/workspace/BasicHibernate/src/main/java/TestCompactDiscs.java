import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.conygre.training.entities.*;

public class TestCompactDiscs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = 
					Persistence.createEntityManagerFactory("conygrePersistentUnit");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		CompactDisc disc = em.find(CompactDisc.class, 11);

		System.out.println(disc.getTitle());

		em.clear();

		disc.setTitle("A Rush of Blood to the Head");
		//disc.setTitle("Mylo Xyloto");


		Query allSpiceGirlsTracks = em.createQuery("select t.title from Track t where t.cdId  = 16");
		allSpiceGirlsTracks.getResultList().forEach(System.out::println);
		tx.commit();
		em.close();

		factory.close();
	}

}
