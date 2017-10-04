import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.spring.service.CompactDiscService;
import com.conygre.training.entities.CompactDiscMongo;

public class TestSpringData {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CompactDiscService service = context.getBean("compactDiscService", CompactDiscService.class);
		service.getCds().forEach(cd -> System.out.println(cd.getTitle())); 
			
		service.getCDsByArtist("Spice Girls").forEach(cd -> System.out.println(cd.getTitle())); 
		
		CompactDiscMongo disc = new CompactDiscMongo("The Wall", 12.0, "Pink Floyd", 10);
		
		//service.addMongoCD(disc);
		
		//service.getMongoCDs().forEach(m -> System.out.println(m.getArtist()));
		
	}
}
