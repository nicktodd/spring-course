package com.conygre.spring.boot.rest;

import com.conygre.spring.boot.AppConfig;
import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.services.CompactDiscService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Nick Todd on 30/08/2017.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CompactDiscController.class)
@ContextConfiguration(classes={com.conygre.spring.boot.AppConfig.class})
//@TestPropertySource(locations = "classpath:application-test.properties") // needed in SpringBoot 2.0.x as Swagger breaks tests
public class TestCompactDiscController {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompactDiscService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testCanRetrieveAllCDs() throws Exception {

        CompactDisc disc = new CompactDisc("Abba Gold", 1, "Abba", 10);
        List<CompactDisc> allDiscs = Arrays.asList(disc);

        given(service.getCatalog()).willReturn(allDiscs);

        mockMvc.perform(get("/api/compactdiscs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Abba Gold")));
    }


    @Test
    public void testCanAddCD() throws Exception {

        CompactDisc disc = new CompactDisc("Abba Gold", 1, "Abba", 10);
        mockMvc.perform(post("/api/compactdiscs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(disc)))
                .andExpect(status().isOk());
        verify(service).addNewCompactDisc(any(CompactDisc.class));

    }


}
