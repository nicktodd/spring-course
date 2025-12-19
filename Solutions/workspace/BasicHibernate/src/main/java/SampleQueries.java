import java.util.List;

import jakarta.persistence.*;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.entities.Track;

public class SampleQueries {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygrePersistentUnit");
		EntityManager em = factory.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// Use TypedQuery for CompactDisc queries
		TypedQuery<CompactDisc> query = em.createQuery("select c from CompactDisc c", CompactDisc.class);
		query.setFirstResult(2);
		query.setMaxResults(5);
		List<CompactDisc> emps = query.getResultList();
		System.out.println("Pagination");
		for (CompactDisc disc : emps)
			System.out.println(disc.getId());
		System.out.println("Pagination end");


		// single results
		TypedQuery<CompactDisc> querySingle = em.createQuery("select c from CompactDisc c where c.id = :id", CompactDisc.class);
		querySingle.setParameter("id", 12);
		CompactDisc cd = querySingle.getSingleResult();

		// Use TypedQuery for named query
		TypedQuery<CompactDisc> nQuery = em.createNamedQuery("compactdisc.findByPrice", CompactDisc.class);
		nQuery.setParameter("price", 12.0);
		List<CompactDisc> discs = nQuery.getResultList();
		System.out.println("Named query by price");
		for (CompactDisc disc : discs) {
			System.out.println(disc.getTitle());
		}

		// Example Where clause
		System.out.println("Here are all the CDs more than 12 pounds");
		TypedQuery<CompactDisc> allCDsBeginningWithA = em.createQuery(
				"select cd from CompactDisc as cd where cd.price > 12 order by cd.title", CompactDisc.class);
		List<CompactDisc> cdsOver12 = allCDsBeginningWithA.getResultList();

		em.refresh(cdsOver12.get(0));

		for (CompactDisc compactDisc : cdsOver12) {
			System.out.println(compactDisc.getTitle());
		}

		// All Titles of CDs
		System.out.println("Here are all the titles from title List - more efficient");
		TypedQuery<String> allTitles = em.createQuery("select cd.title from CompactDisc as cd", String.class);
		List<String> titles = allTitles.getResultList();

		for (String title : titles) {
			System.out.println(title);
		}

		// All titles and artists
		System.out.println("Here are all the titles and artists - more efficient");
		TypedQuery<Object[]> allTitlesAndArtists = em.createQuery(
				"select cd.title, cd.artist from CompactDisc as cd", Object[].class);
		List<Object[]> titlesArtists = allTitlesAndArtists.getResultList();

		for (Object[] result : titlesArtists) {
			System.out.println(result[0] + " , " + result[1]);
		}

		// Use TypedQuery for join query
		TypedQuery<Track> joinQuery = em.createQuery(
				"select t from CompactDisc c inner join c.trackTitles t", Track.class);
		List<Track> tracks = joinQuery.getResultList();
		for (Track t : tracks) {
			System.out.println(t.getTitle());
		}
		tx.commit();

		em.close();

		factory.close();

	}

}
