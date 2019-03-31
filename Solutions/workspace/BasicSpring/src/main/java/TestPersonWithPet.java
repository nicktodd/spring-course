import com.cisco.training.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonWithPet {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("apprentices.xml");

        Person p = context.getBean("person", Person.class);

        p.getPet().feed();


    }
}
