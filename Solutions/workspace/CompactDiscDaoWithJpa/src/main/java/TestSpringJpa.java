import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.conygre.spring.configuration.AppConfig;
import com.conygre.spring.service.CompactDiscService;


public class TestSpringJpa {

	public static void main(String[] args) {
		
		ApplicationContext context =
				new  AnnotationConfigApplicationContext(AppConfig.class);
		CompactDiscService service = context.getBean(CompactDiscService.class);
		
		service.getCatalog().forEach(c -> System.out.println(c.getTitle()));
		
		
	}

}
