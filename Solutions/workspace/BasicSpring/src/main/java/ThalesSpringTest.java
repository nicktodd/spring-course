import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.spring.beans.Furniture;
import com.conygre.spring.beans.Room;
import com.conygre.spring.beans.Table;
import com.conygre.spring.components.Car;

public class ThalesSpringTest {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("thales.xml");
		Furniture t = context.getBean("myTable", Table.class);
		
		Room diningRoom = context.getBean("diningRoom", Room.class);
		Room kitchen = context.getBean("kitchen", Room.class);
		System.out.println(diningRoom.getFurnitureItem().getMaterial());
		System.out.println(kitchen.getFurnitureItem().getMaterial());
		
		Car c = context.getBean("car", Car.class);
		System.out.println(c.getPetrolEngine().getEngineSize());
		
		
	}

}
