// This class demonstrates the N+1 selects problem in JPA/Hibernate.
// The initial query loads all CompactDisc entities (1 query),
// but for each CompactDisc, accessing its trackTitles (if lazily loaded)
// triggers an additional query per disc, resulting in N+1 total queries.
// This is inefficient and known as the N+1 selects problem.

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.entities.Track;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class NPlusOneClass {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygrePersistentUnit");
        EntityManager em = factory.createEntityManager();

        TypedQuery<CompactDisc> query = em.createQuery("select c from CompactDisc c", CompactDisc.class);
        List<CompactDisc> discs = query.getResultList();

        em.close();
        factory.close();
        
        for(CompactDisc disc : discs) {
            System.out.println(disc.getArtist());
            for (Track t : disc.getTrackTitles()) {
                System.out.println(t.getTitle());
            }
        }

        // --- N+1 Solution: Use JOIN FETCH to eagerly load tracks with discs ---
        EntityManagerFactory factory2 = Persistence.createEntityManagerFactory("conygrePersistentUnit");
        EntityManager em2 = factory2.createEntityManager();
        TypedQuery<CompactDisc> query2 = em2.createQuery(
            "select distinct c from CompactDisc c join fetch c.trackTitles", CompactDisc.class);
        List<CompactDisc> discsWithTracks = query2.getResultList();
        em2.close();
        factory2.close();
        System.out.println("\n--- Solution: Avoiding N+1 Problem ---");
        for(CompactDisc disc : discsWithTracks) {
            System.out.println(disc.getArtist());
            for (Track t : disc.getTrackTitles()) {
                System.out.println(t.getTitle());
            }
        }
        
    }

}
