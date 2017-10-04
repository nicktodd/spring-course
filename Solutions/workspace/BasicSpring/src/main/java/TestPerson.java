import com.conygre.spring.beans.Family;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.spring.beans.Person;
import com.conygre.spring.components.Car;




public class TestPerson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("mybeans.xml");
		Person p = context.getBean("myPerson", Person.class);
		System.out.println(p.getAddress().getLine1());
		
		// get the car
		Car c = context.getBean("car", Car.class);
		System.out.println(c.getMake());

		Family myFamily = context.getBean("family", Family.class);
		System.out.println(myFamily.getFather().getName());
		System.out.println(myFamily.getMother().getName());
		System.out.println(myFamily.getSon().getName());
		System.out.println(myFamily.getDaughter().getName());
		
	}

}
