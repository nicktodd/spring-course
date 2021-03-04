package com.conygre.spring.boot.repo;

import com.conygre.spring.boot.repos.CompactDiscRepository;
import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.rest.CompactDiscController;
import com.conygre.spring.boot.services.CompactDiscService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
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


    @Autowired
    CompactDiscController controller;

    @Before
    public  void setupDatabaseEntryForReadOnlyTests() {
        CompactDisc disc = new CompactDisc("Abba Gold", 12.99, "Abba", 5);
        manager.persist(disc);
    }



    // unit test the repo using a mock database
    @Test
    public void canRetrieveCDByArtist() {
        Iterable<CompactDisc> discs = repo.findByArtist("Abba");
        Stream<CompactDisc> stream = StreamSupport.stream(discs.spliterator(), false);
        assertThat(stream.count(), equalTo(1L));
    }


    // integration test for the service layer and data layer
    @Test
    public void compactDiscServiceCanReturnACatalog() {
        Iterable<CompactDisc> discs = discService.getCatalog();
        Stream<CompactDisc> stream = StreamSupport.stream(discs.spliterator(), false);
        Optional<CompactDisc> firstDisc = stream.findFirst();
        assertThat(firstDisc.get().getArtist(), equalTo("Abba"));
    }

    // integration test with the controller
    @Test
    public void controllerCanReturnCDById() {
        CompactDisc cd = controller.getCdById(1);
        assertThat(cd.getArtist(), equalTo("Abba"));
    }

}
