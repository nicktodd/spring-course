# Spring Data with Mongo

## Aims
In this exercise, you will try out creating a DAO using Spring data. This should not take long since as you have seen in the chapter, Spring data massively simplifies the creation of data access objects.

We will migrate the previous exercise application that uses the MongoTemplate to use Spring Data instead. 

## Part 1 Create your Project
1.	Copy either your project solution to the previous exercise or the solution project which is called ****CompactDiscDaoMongoDBSpringGradle** (if you are in Eclipse, you can copy and paste this directly from within the ****Package Explorer**.
2.	Open the newly copied project in your preferred IDE.
3.	Open the settings.gradle file and rename the root project name to MongoDBSpringData. 

## Part 2: Create a Spring Data Repository
1.	Create a new Java interface called com.conygre.spring.data.CompactDiscRepository.
2.	Mark it to extend MongoRepository<CompactDisc, ObjectId>.
3.	For now, you don’t need to do anything else, since it will have all the methods for Create, Read, Update and Delete for CompactDiscs.
4.	Open the CompactDiscService class and change the reference to a CompactDiscDAO type to your new CompactDiscRepository instead which will cause various errors in it. 
5.	Refactor the getCatalog() method to use the findAll() method of the CompactDiscRepository. Remember findAll() is provided automatically by your new repository interface.
6.	Refactor the addToCatalog() method to use the insert(cd) method of your repository.
7.	In this exercise, you will use the annotations to inject your repository, so annotate the CompactDiscRepository property with @Autowired. An example is shown below:

import com.conygre.spring.data.CompactDiscRepository;
import com.conygre.spring.entities.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CompactDiscService {

@Autowired
 private CompactDiscRepository dao;


 public void addToCatalog(CompactDisc disc) {
        dao.insert(disc);
    }

 public Collection<CompactDisc> getCatalog() {
        return dao.findAll();
    }

}

8.	To enable the repository to be available, you will need to add a new configuration annotation to your MongoJavaConfig class, so open MongoJavaConfig.java, and add this additional annotation:

@EnableMongoRepositories(basePackages = "com.conygre.spring.data")

 
9.	Finally, run your unit tests as before where it calls the addToCatalog() method. You will see a working service layer!

You now have a new version of your application that works with Spring Data instead of your own hand written DAO class. You can see how it simplifies the code you are required to write.

## Part 3 (Optional) Add some of your own custom queries
If you have time, try adding some of your own query methods to the repository and test them from the Service bean to see if they work. For example, search by artist and title, or search by price. You could also add in another test to check for the getCatalog() method of the service bean.

