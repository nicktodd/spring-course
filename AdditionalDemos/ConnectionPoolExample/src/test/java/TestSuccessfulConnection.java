import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSuccessfulConnection {

	@Test
	public void testCanConnectUsingConnectionPool() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BasicDataSource  ds = context.getBean("dataSource", BasicDataSource.class);
		Connection conn = ds.getConnection();
		conn.close();
		
		
		
	}
}
