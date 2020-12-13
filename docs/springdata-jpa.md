# Spring Data with JPA

## Aims

In this exercise, you will try out creating a DAO using Spring data. This should not take long since as you have seen in the chapter, Spring data massively simplifies the creation of data access objects.

We will migrate the previous exercise application that uses JPA to use Spring Data instead. 

## Part 1 Create your Project

1.	Copy either your project solution to the previous exercise or the solution project which is called CompactDiscDAOWithJPA (if you are in Eclipse, you can copy and paste this directly from within the Package Explorer.

2.	Open the newly copied project in your preferred IDE.

3.	Open the pom file and rename the artifact ID to CompactDiscDaoSpringData. 

4.	Also in the pom file, replace the entire <dependencies> section with 

```
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.data</groupId>
		<artifactId>spring-data-releasetrain</artifactId>
			<version>Lovelace-RELEASE</version>
			<scope>import</scope>
			<type>pom</type>
		</dependency>
	</dependencies>
</dependencyManagement>
```

5.	Then, below the dependencyManagement section, add a new `dependencies` element, and add the following three dependencies. Note that the spring-data has no version specified. This is because it is determined by the bill of materials added above. You also need a current version of Hibernate and you also need the database driver for MySQL.

```
<dependencies>
	<dependency>
		<groupId>org.springframework.data</groupId>
		<artifactId>spring-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>8.0.22</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-entitymanager</artifactId>
		<version>5.0.7.Final</version>
	</dependency>
</dependencies>
```

6.	Now finally, add the build entry to force Maven to treat your project as Java 8.

```
<build>
	<plugins>
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.2</version>
			<configuration>
				<source>1.8</source>
				<target>1.8</target>
			</configuration>
		</plugin>
	</plugins>
</build>
```

That is the pom file complete, so you will now make the necessary changes to the rest of the project.

## Part 2: Create a Spring Data Repository

1.	Create a new Java interface called com.conygre.spring.data.repos.CompactDiscRepository.

2.	Mark it to extend JpaRepository<CompactDisc, Integer>.

3.	For now, you don’t need to do anything else, since it will have all the methods for Create, Read, Update and Delete for CompactDiscs.

4.	Open the CompactDiscService class and change the reference to a CompactDiscDAO type to your new CompactDiscRepository instead which will cause various errors in it. For now we will focus on the getCatalog() method, so refactor that to use the findAll() method of the CompactDiscRepository. Remember findAll() is provided automatically by your new repository interface.

5.	In this exercise, you will use the annotations to inject your repository, so annotate the CompactDiscRepository property with @Autowired. An example is shown below:

```
@Service
public class CompactDiscService {

	@Autowired
	private CompactDiscRepository repository;
	
	public List<CompactDisc> getCds() {
		return repository.findAll();
	}
}
```

6.	To enable the repository to be available, you will need to add a new configuration annotation to your JpaConfiguration class, so open JpaConfiguration.java, and add this additional annotation:

```
@EnableJpaRepositories(basePackages = "com.conygre.spring.data.repos")
```

7.	Finally, open your main method and run it as you did before where it calls the getCatalog() method. You will see a list of compact discs if all has been done correctly.

You now have a new version of your application that works with Spring Data instead of Spring JPA. You can see how it simplifies the code you are required to write.

## Part 3 (Optional) Add some of your own custom queries

If you have time, try adding some of your own query methods to the repository and test them from the Service bean to see if they work. For example, search by artist and title, or search by price.

