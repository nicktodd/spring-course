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



/* This test requires a specific version of Mockito in the pom when using Java 11 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestCompactDiscControllerUnitTest.Config.class)
public class TestCompactDiscControllerUnitTest {


    protected static class Config {


        // create a mock service layer than when asked for all the CDs returns a single CD in a list
        @Bean
        public CompactDiscService service() {
            CompactDisc cd = new CompactDisc();
            cd.setTitle("Harry's House");
            cd.setArtist("Harry Styles");
            List<CompactDisc> cds = new ArrayList<>();
            cds.add(cd);

            CompactDiscService service = mock(CompactDiscService.class);
            when(service.getCatalog()).thenReturn(cds);
            when(service.getCompactDiscById(1)).thenReturn(cd);
            return service;
        }

        @Bean
        public CompactDiscController controller() {
            return new CompactDiscController();
        }
    }

    @Autowired
    private CompactDiscController controller;

    @Test
    public void testFindAll() {
        Iterable<CompactDisc> cds = controller.findAll();
        Stream<CompactDisc> stream = StreamSupport.stream(cds.spliterator(), false);
        cds.forEach(cd -> System.out.println(cd.getArtist()));
        assertThat(stream.count(), equalTo(1L));
    }

    @Test
    public void testCdById() {
        CompactDisc cd = controller.getCdById(1);
        assertNotNull(cd);
    }

}
