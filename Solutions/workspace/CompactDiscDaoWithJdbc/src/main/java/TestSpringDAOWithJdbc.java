import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringDAOWithJdbc {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CompactDiscSpringDAO dao = context.getBean("dao", CompactDiscSpringDAO.class);
		CompactDisc disc = dao.getCdById(14);
		System.out.println(disc.getArtist());
	}

}
