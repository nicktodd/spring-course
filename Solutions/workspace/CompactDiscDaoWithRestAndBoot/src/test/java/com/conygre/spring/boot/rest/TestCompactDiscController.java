package com.conygre.spring.boot.rest;

import com.conygre.spring.boot.AppConfig;
import com.conygre.spring.boot.services.CompactDiscService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Nick Todd on 30/08/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CompactDiscController.class)
//@SpringBootTest(classes={com.conygre.spring.boot.AppConfig.class})
public class TestCompactDiscController {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompactDiscService service;


    @Test
    public void testThatCanConnect() throws Exception {

        mockMvc.perform(get("/api/compactdiscs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].title", is("Abba")));



    }



}
