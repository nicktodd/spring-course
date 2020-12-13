# Spring Boot with JPA

## Aims

In this exercise you will see how easy it is to build a complete end to end application using much of the technology you have covered in the previous chapters, but with much of the code and configuration no longer required.


## Part 1 Create a Spring Boot Application
1.	Create a new Maven project with an artifact ID MySpringBoot and a group ID of com.conygre.spring.boot.

2.	Add the following parent pom entry:

```
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.1.3.RELEASE</version>
</parent>
```

3.	Now add the following 3 dependencies:
```
<dependencies>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>8.0.22</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
</dependencies>
```
4.	Finally add the spring boot plugin:
```
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```

## Part 2 Create and Include the Required Classes
1.	Begin by copying the CompactDisc and Track entity classes into your project.

2.	Copy in your Spring Data Repository interface from the Spring Data exercise.

3.	Copy over your CompactDiscService class from your Spring Data exercise, and define an interface for it.

```
public interface CompactDiscService {
	Iterable<CompactDisc> getCatalog();
}


@Service
public class CompactDiscServiceImpl implements CompactDiscService {
	
	@Autowired
	private CompactDiscRepository dao;
	
	
	public List<CompactDisc> getCatalog() {
		return dao.findAll();
	}
}
```

4.	Create a REST class annotated as shown with an implementation of the CompactDiscService injected:

```
@RestController
@RequestMapping("/api/compactdiscs")
public class CompactDiscController {

	 @Autowired
	  private  CompactDiscService service;
	
	  @RequestMapping(method = RequestMethod.GET)
	    List<CompactDisc> findAll() {
	        return service.getCatalog();
	    }
}
```

5.	Finally, create an application class called AppConfig and annotate it with the following Spring Boot annotations:

```
@EnableAutoConfiguration
@ComponentScan
```

6.	Add a main method to the class and in main, add the following API call:

```
SpringApplication.run(AppConfig.class, args);
```

7.	Finally, in src\main\resources, create a new file called application.properties, and add into it the following database connection properties:

```
spring.datasource.url=jdbc:mysql://localhost:3306/conygre?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=c0nygre
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
```

Note: Make sure there are no spaces at the end of the lines, as they get picked up and treated as part of the values!

8.	The project is complete, so now run the AppConfig class.

9.	Visit your new REST API in the browser and it will return your compact discs. 

The URL will be: http://localhost:8080/api/compactdiscs

## Part 3 Extend the Application to include additional REST methods

Now you have successfully created a Spring Boot application, enhance the REST API to incorporate additional methods and implement the necessary functions in the service layer and repository layer.


