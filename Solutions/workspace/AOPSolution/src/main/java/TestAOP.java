import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conygre.training.aop.BarTender;
import com.conygre.training.aop.IBarTender;


public class TestAOP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		IBarTender barTender = context.getBean("barTender", IBarTender.class);
		
		barTender.pullPint("fosters");
		
		barTender.pullPint("guinness");
		
		
		
		barTender.pullPint();
		
		barTender.serveWhiskey();
		
		// probably enough drinks. Time for a coffee
		
		barTender.makeHotCoffee();
		
	}

}
