import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import com.conygre.training.entities.CompactDisc;
import com.conygre.training.remoting.http.CompactDiscService;

public class RemoteCompactDiscServiceTest {

	

	@Test
	public void testThatCollectionOfCompactDiscsCanBeRetrievedRemotelyUsingHessian() {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"clientContext.xml");

		// Remote User Service is called via Http Client Application Context...
		CompactDiscService hessianClient = (CompactDiscService) context
				.getBean("cdHessianService");
		
		System.out.println("Results via Hessian");
		for (CompactDisc disc : hessianClient.getAllDiscs()) {
			System.out.println(disc.getTitle());
		}
		
		assertTrue(hessianClient.getAllDiscs().size() > 1);
		

	}

	
	
}
