package functional.tests;


import com.conygre.spring.boot.entities.CompactDisc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;


public class CompactDiscRestTests {

    private RestTemplate template = new RestTemplate();

    @Disabled
    @Test
    public void testFindAll() {
        List<CompactDisc> cds = template.getForObject("http://localhost:8080/api/compactdiscs", List.class);
        assertThat(cds.size(),  greaterThan(1));
    }

    @Disabled
    @Test
    public void testCdById() {
        CompactDisc cd = template.getForObject
                ("http://localhost:8080/api/compactdiscs/16", CompactDisc.class);
        assertThat(cd.getArtist(), equalTo("Spice Girls"));
    }

}
