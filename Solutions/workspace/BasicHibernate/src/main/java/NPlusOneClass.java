import com.conygre.training.entities.CompactDisc;
import com.conygre.training.entities.Track;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class NPlusOneClass {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygrePersistentUnit");
        EntityManager em = factory.createEntityManager();

        List<CompactDisc> discs = em.createQuery("from CompactDisc").getResultList();




        em.close();
        factory.close();


        for(CompactDisc disc : discs) {
            System.out.println(disc.getArtist());
            for (Track t : disc.getTrackTitles()) {
                System.out.println(t.getTitle());
            }
        }


    }

}
