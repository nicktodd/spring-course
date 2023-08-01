package hello;

import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CassandraRepository<Customer> {

    @Override
    List<Customer> findAll();

    // NOTE, be very careful about ALLOW FILTERING in real world apps, this 
    // may affect scalability quite a lot. Filtering is efficient over primary
    // keys, not on all generic columns
    @Query("SELECT * FROM Customer WHERE lastName = ?0 ALLOW FILTERING")
    List<Customer> findByLastName(String lastName);
    
}
