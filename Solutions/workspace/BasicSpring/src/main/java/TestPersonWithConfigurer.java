import com.conygre.test.pets.PetConfigurer;
import com.conygre.test.pets.PetConfigurerAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.conygre.test.pets.Person;

public class TestPersonWithConfigurer {
	
	public static void main(String[] args) {
		ApplicationContext context = new  AnnotationConfigApplicationContext(PetConfigurerAnnotations.class);
		context.getBean(Person.class).getPet().feed();
	}
}
