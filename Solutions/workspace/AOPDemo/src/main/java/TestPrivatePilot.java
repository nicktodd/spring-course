import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.training.flying.Pilot;

public class TestPrivatePilot {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Pilot pilot = (Pilot)context.getBean("privatePilot");
		pilot.takeOff();
		pilot.navigate();
		pilot.land();		
		
		System.out.println(pilot.getClass());
	}
}
