package com.conygre.spring.boot.controller;

import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.repos.CompactDiscRepository;
import com.conygre.spring.boot.rest.CompactDiscController;
import com.conygre.spring.boot.services.CompactDiscService;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/* The beans need to be commented out otherwise other tests will fail as they pick up the
configured beans in this test!
 */

/* This test also requires a specific version of Mockito in the pom when using Java 11 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestCompactDiscControllerUnitTest.Config.class)
public class TestCompactDiscControllerUnitTest {

    // Define a configuration class used for our test
    // it is static so there is only one instance of it
    // NOTE breaks other tests as the scope of this configuration goes to all test classes
    //@TestConfiguration
    protected static class Config {

        // needed for the Spring repo dependency in the service layer
        @Bean
        @Primary
        public CompactDiscRepository repo() {
            return mock(CompactDiscRepository.class);
        }

        // create a mock service layer than when asked for all the CDs returns a single CD in a list
        @Bean
        @Primary
        public CompactDiscService service() {
            CompactDisc cd = new CompactDisc();
            List<CompactDisc> cds = new ArrayList<>();
            cds.add(cd);

            CompactDiscService service = mock(CompactDiscService.class);
            when(service.getCatalog()).thenReturn(cds);
            when(service.getCompactDiscById(1)).thenReturn(cd);
            return service;
        }

        @Bean
        @Primary
        public CompactDiscController controller() {
            return new CompactDiscController();
        }
    }

    @Autowired
    private CompactDiscController controller;

    @Disabled
    @Test
    public void testFindAll() {
        Iterable<CompactDisc> cds = controller.findAll();
        Stream<CompactDisc> stream = StreamSupport.stream(cds.spliterator(), false);
        assertThat(stream.count(), equalTo(1L));
    }

    @Disabled
    @Test
    public void testCdById() {
        CompactDisc cd = controller.getCdById(1);
        assertNotNull(cd);
    }

}
