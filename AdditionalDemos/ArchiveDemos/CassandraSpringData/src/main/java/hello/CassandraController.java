package hello;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nick Todd on 06/06/2017.
 */
@RestController
@RequestMapping("/")
public class CassandraController {


    private CustomerRepository repo;

    @Required
    public void setRepository(CustomerRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return repo.findAll();
    }


}
