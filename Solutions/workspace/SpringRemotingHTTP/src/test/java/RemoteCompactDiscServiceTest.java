import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import org.junit.Ignore;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.remoting.http.CompactDiscService;

public class RemoteCompactDiscServiceTest {

	@Test
	
	public void testThatCollectionOfCompactDiscsCanBeRetrievedRemotelyUsingHTTP() {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"clientContext.xml");

		// Remote User Service is called via Http Client Application Context...
		CompactDiscService httpClient = (CompactDiscService) context
				.getBean("cdService");
		System.out.println("Results via HTTP");
		for (CompactDisc disc : httpClient.getAllDiscs()) {
			System.out.println(disc.getTitle());
		}
		
		assertTrue(httpClient.getAllDiscs().size() > 1);
		

	}


	

	
	
}
