package com.conygre.spring.boot.repo;

import com.conygre.spring.boot.repos.CompactDiscRepository;
import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.services.CompactDiscService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by Nick Todd on 30/08/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest // use an in memory database
@SpringBootTest(classes={com.conygre.spring.boot.AppConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests
public class TestCDRepository {

    @Autowired
    private TestEntityManager manager;

    @Autowired // this is a mock which is injected because of the @DataJpaTest
    private CompactDiscRepository repo;

    @Autowired
    CompactDiscService discService;

    @Test
    public void canRetrieveCDByArtist() {
        CompactDisc disc = new CompactDisc("Abba Gold", 12.99, "Abba", 5);
        //disc.setId(1);
        manager.persist(disc);

        Iterable<CompactDisc> discs = repo.findByArtist("Abba");
        boolean result = false;
        for (CompactDisc current : discs) {
            if (current.getArtist().equals("Abba")) {
                result = true;
                break;
            }
        }
        assertTrue(result);
    }


    @Test
    public void compactDiscServiceCanReturnACatalog() {
        CompactDisc disc = new CompactDisc("Abba Gold", 12.99, "Abba", 5);
        //disc.setId(1);
        manager.persist(disc);
        Iterable<CompactDisc> discs = discService.getCatalog();
        boolean result = false;
        for (CompactDisc current : discs) {
            if (current.getArtist().equals("Abba")) {
                result = true;
                break;
            }
        }
        assertTrue(result);

    }

}
