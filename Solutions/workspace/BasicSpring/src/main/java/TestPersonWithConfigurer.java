import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.conygre.spring.beans.Person;
import com.conygre.spring.components.Car;
import com.conygre.spring.java.configurer.PersonConfigurer;

public class TestPersonWithConfigurer {
	
	public static void main(String[] args) {
		ApplicationContext context = new  AnnotationConfigApplicationContext(PersonConfigurer.class);
		System.out.println(context.getBean(Person.class).getAddress().getLine1());
		
		System.out.println(context.getBean(Car.class).getPetrolEngine().getEngineSize());
		
	}

}
