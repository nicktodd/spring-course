package controllers;

import com.conygre.spring.boot.AppConfig;
import com.conygre.spring.boot.repos.CompactDiscRepository;
import com.conygre.spring.boot.rest.CompactDiscController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AppConfig.class)
public class TestCompactDiscController {

    @Autowired
    private CompactDiscController controller;


    @Test
    public void testCompactDiscsReturnedByController() {
       assertTrue(controller.findAll() != null);
       controller.findAll().forEach(m -> System.out.println(m.getTitle()));

    }







}
