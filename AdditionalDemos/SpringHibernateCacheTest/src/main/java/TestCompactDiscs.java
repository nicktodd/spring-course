import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.entities.Track;


public class TestCompactDiscs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygre");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//CompactDisc cd = em.find(CompactDisc.class, 9);
		//System.out.println(cd.getTitle());
//		
//		// Relationships exercise: Getting the tracks out
//		Collection<Track> tracks = cd.getTrackTitles();
//		
//		for (Track track : tracks) {
//			System.out.println(track.getTitle());
//		}
//		
//		CompactDisc disc = new CompactDisc("Ice on the Dew", 12.99, "Empire of the Sun", 12);
//		disc.setId(9);
//		
//		em.merge(disc);
//		
//		
//		Query allCDs = em.createQuery("from CompactDisc");// cd where cd.artist = 'Abba Gold'");
//		List<CompactDisc> result = allCDs.getResultList();
//		
//		for (CompactDisc compactDisc : result) {
//			System.out.println(compactDisc.getTitle());
//		}
		
		Query query = em.createNamedQuery("compactdisc.getAll");
		query.setParameter("price", 13.0);
		
		List<CompactDisc> discs = query.getResultList();
		for (CompactDisc compactDisc : discs) {
			System.out.println(compactDisc.getTitle());
		}
		
		
		// Adding a new CD with tracks
//		CompactDisc myCD = new CompactDisc("Loud", 12.99, "Rihanna", 8);
//		myCD.addTrack(new Track("SandM"));
//		myCD.addTrack(new Track("What's my name?"));
//		
//		em.persist(myCD);
		
		// Queries exercise
		
		// ALL CDs beginning with artist beginning with S
//		System.out.println("Here are all the CDs beginning with the letter S");
//		Query allCDsBeginningWithA = em.createQuery("from CompactDisc as cd where cd.artist like 'S%'");
//		List<CompactDisc> cdsA = allCDsBeginningWithA.getResultList();
//		
//		for (CompactDisc compactDisc : cdsA) {
//			System.out.println(compactDisc.getArtist());
//		}
//		
		// TOTAL number of CDs
		Query cdCount = em.createQuery("select count(*) from CompactDisc");
		System.out.println("The number of CDs is " + cdCount.getSingleResult());
		
//		// ALL CDs in Alphabetic order by title
//		System.out.println("Here are all the CDs in alphabetical order");
//		Query allCDsAlphabeticQuery = em.createQuery("from CompactDisc as cd order by cd.title");
//		List<CompactDisc> cdsAlphabetic = allCDsAlphabeticQuery.getResultList();
//		for (CompactDisc compactDisc : cdsAlphabetic) {
//			System.out.println(compactDisc.getTitle());
//		}
//		
//		// All tracks by the Spice Girls
//		System.out.println("Here are all the Spice Girls tracks");
//		Query allSpiceGirlTracks = em.createQuery("select t.title from Track t where t.cdId = 16");
//		List<String> trackTitles = allSpiceGirlTracks.getResultList();
//		for (String trackTitle : trackTitles) {
//			System.out.println(trackTitle);
//		}
//		
//		// A parameterised query for a title and the matching CD will be returned
//		System.out.println("Please enter a title:");
//		Scanner sc = new Scanner(System.in);
//		String title = sc.next();
//		
//		Query titleQuery = em.createQuery("from CompactDisc cd where cd.title= :title");
//		titleQuery.setParameter("title", title);
//		
//		CompactDisc titledCD   = (CompactDisc)titleQuery.getSingleResult();
//		
//		System.out.println("The CD returned is by " + titledCD.getArtist());
//		
//		
//		// All tracks on album 16
//		System.out.println("Here are all the tracks and the title of Album 16");
//		Query allAlbum16Tracks = em.createQuery("select cd.title, t.title from CompactDisc cd, Track t where cd.id = 16");
//		List<Object[]> albumAndTitle = allAlbum16Tracks.getResultList();
//		for (Object[] current: albumAndTitle) {
//			System.out.println(current[0] + "  " + current[1]);
//		}
		
		tx.commit();
		em.close();
	}

}
