import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

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


		TypedQuery<String> allSpiceGirlsTracks = em.createQuery("select t.title from Track t where t.cdId  = 16",String.class);
		allSpiceGirlsTracks.getResultList().forEach(System.out::println);
		tx.commit();
		em.close();

		factory.close();
	}

}
