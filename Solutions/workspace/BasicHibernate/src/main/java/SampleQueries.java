import java.util.List;

import javax.persistence.*;

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

		TypedQuery<CompactDisc> query = em.createQuery("from CompactDisc", CompactDisc.class);
		query.setFirstResult(2);
		query.setMaxResults(5);

		List<CompactDisc> emps = query.getResultList();
		System.out.println("Pagination");
		for (CompactDisc disc : emps)
			System.out.println(disc.getId());
		System.out.println("Pagination end");

		// use the named query
		Query nQuery = em.createNamedQuery("compactdisc.findByPrice");
		nQuery.setParameter("price", 12.0);
		List<CompactDisc> discs = nQuery.getResultList();
		System.out.println("Named query by price");
		for (CompactDisc disc : discs) {
			System.out.println(disc.getTitle());
		}

		// Example Where clause
		System.out.println("Here are all the CDs more than 12 pounds");
		Query allCDsBeginningWithA = em
				.createQuery("select cd from CompactDisc as cd where cd.price > 12 order by cd.title");
		List<CompactDisc> cdsOver12 = allCDsBeginningWithA.getResultList();

		em.refresh(cdsOver12.get(0));

		for (CompactDisc compactDisc : cdsOver12) {
			System.out.println(compactDisc.getTitle());
		}

		// All Titles of CDs
		System.out.println("Here are all the titles from title List - more efficient");
		Query allTitles = em.createQuery("select cd.title from CompactDisc as cd");
		List<String> titles = allTitles.getResultList();

		for (String title : titles) {
			System.out.println(title);
		}

		// All titles and artists
		System.out.println("Here are all the titles from title List - more efficient");
		Query allTitlesAndArtists = em.createQuery("select cd.title, cd.artist from CompactDisc as cd");
		List<Object[]> titlesArtists = allTitlesAndArtists.getResultList();

		for (Object[] result : titlesArtists) {
			System.out.println(result[0] + " , " + result[1]);
		}
		// Query allCDsWithTracks = em.createQuery("select cd from CompactDisc cd, Track
		// t where count(cd.trackTitles) > 0");
		// List<CompactDisc> cdsWithTracks = allCDsWithTracks.getResultList();
		//
		// for (CompactDisc compactDisc : cdsWithTracks) {
		// System.out.println(compactDisc.getTitle());
		// }

		Query joinQuery = em.createQuery("select t from CompactDisc c inner join c.trackTitles t");
		List<Track> tracks = joinQuery.getResultList();
		for (Track t : tracks) {
			System.out.println(t.getTitle());
		}
		tx.commit();

		em.close();

		factory.close();

	}

}
