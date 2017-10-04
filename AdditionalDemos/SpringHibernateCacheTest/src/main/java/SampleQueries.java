import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.conygre.training.entities.CompactDisc;


public class SampleQueries {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygre");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// Example Where clause
		System.out.println("Here are all the CDs more than 12 pounds");
		Query allCDsBeginningWithA = em.createQuery("from CompactDisc as cd where cd.price > 12 order by cd.title");
		List<CompactDisc> cdsOver12 = allCDsBeginningWithA.getResultList();
		
		for (CompactDisc compactDisc : cdsOver12) {
			System.out.println(compactDisc.getTitle());
		}
		
		// All Titles of CDs 
		System.out.println("Here are all the titles from title List - more efficient");
		Query allTitles= em.createQuery("select cd.title from CompactDisc as cd");
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
//		Query allCDsWithTracks = em.createQuery("select cd from CompactDisc cd, Track t where count(cd.tracks) > 0");
//		List<CompactDisc> cdsWithTracks = allCDsWithTracks.getResultList();
//		
//		for (CompactDisc compactDisc : cdsWithTracks) {
//			System.out.println(compactDisc.getTitle());
//		}
		
		
	}

}
