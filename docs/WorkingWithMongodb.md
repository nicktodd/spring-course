# Working with MongoDB and Spring using Maven and Eclipse

## Aims
In this exercise, you will create a Spring application that uses MongoDB as the database for a service layer.

## Part 1: Configuring the Project

1.	In the **Project Explorer**, right click and then click **New** and then click Other.
2.	At the **New** dialog, expand **Maven** and then select ** Maven Project**. Click **Next**.
3.	At the New Maven Project dialog, select Create a simple project, and click **Next**.
4.	At the **New Maven Project** dialog, enter the following information:

Group ID	com.conygre.training
Artifact ID	CompactDiscDAOWithMongo
Name	CompactDiscDAOWithMongo

5.Click **Finish**.
6.Open the pom.xml file in your editor (in Eclipse, then click the **Source**** tab).
7.Add the following dependencies to your project (you can paste them from this document). They go directly underneath the <version> element.

<dependencies>
   <dependency>
      <groupId>org.springframework.data</groupId>
      <artifactId>spring-data-mongodb</artifactId>
      <version>1.10.4.RELEASE</version>
   </dependency>
   <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
	<scope>test</scope>
   </dependency>
   <dependency>
      <groupId>org.springframework</groupId>
	<artifactId>spring-test</artifactId>
	<version>4.1.7.RELEASE</version>
	<scope>test</scope>
   </dependency>
</dependencies>

8.	Also add the build element to ensure that it builds using Java 8:

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>


## Create a Document Class
1.	Return to your Java IDE, and in the ****Project Explorer** right click on the src/********main/java** folder and click **New** and then click ****Class**. 
2.	Create the class with the name CompactDisc in the package com.conygre.training.entities.
3.	Add the following properties along with the get/set methods

id	ObjectId
title	String
artist	String
price	double

4.	Add the annotation to specify that the class is a ****@Document**.
5.	Finally, add a constructor to allow you to create a CompactDisc with a title, artist, and price (no ID), and then add a default no argument constructor.

## Setting up the Java Configuration
Initially, you will use the MongoTemplate, so a configuration file will be required.

1.	In your src/main/java folder, create a new Java class called MongoJavaConfig in a package called com.conygre.training.
2.	Annotate the class with @Configuration, and then ensure that the class extends AbstractMongoConfiguration.
3.	Using your IDE, add the required methods. 
4.	Locate the getDatabaseName() method and edit the body of the method to return the String “mydatabase”.
5.	Locate the mongo() method, and return a new MongoClient(“127.0.0.1”, 27017);

## Create a Test Class
1.	Expand src/test/java and within it create a new test class called com.conygre.mongo.BasicMongoTest.
2.	Annotate the class with the @RunWith(SpringJUnit4ClassRunner.class).
3.	Annotate the class with the following additional annotation:

@ContextConfiguration(classes = MongoJavaConfig.class, loader = AnnotationConfigContextLoader.class)

This will cause the test to automatically load your Java 
Springconfiguration class.

4.	Add a private field to the test of type MongoTemplate and annotate it with @Autowired. This will then be automatically set by the SpringJUnit4ClassRunner.
5.	Add a method called canInsertSuccessfully() and annotate it with @Test. 
6.	Using the returned MongoTemplate field, insert a few CompactDisc objects of your choosing into Mongo.
7.	Now retrieve all of the rows from your mongo database as a List and iterate over them displaying all of the titles.
8.	Use an assertEquals to ensure that the right number of rows are added, for example:

assertEquals(3,discs.size());

9.	Finally, we need to add some clean up to remove it all at the end of the test, otherwise, you will keep adding more rows every time! To do this, add a void method called cleanup() and annotate it with @After.
10.	Within this method, add the following code to remove the collection:

@After
public void cleanUp() {
    for (String collectionName : mongoTemplate.getCollectionNames()) {
        if (!collectionName.startsWith("system.")) {
            mongoTemplate.getCollection(collectionName).remove(new BasicDBObject());
        }
    }
}


11.	Make sure mongod.exe is running, and then test your application to ensure that they have been successfully inserted.

## Create a Working Data Access Object
You have a basic test working that proves you can get your Java application to work with MongoDB. Now you will implement the Data Access Object pattern to create a Data access object that can be used to create/read/update/delete rows from your MongoDB collection.

1.	Open your MongoJavaConfig.java file and add the @ComponentScan annotation. This will allow for your DAO and later on, your Service class, to be created automatically.
2.	Create an interface called com.conygre.training.dao.CompactDiscDAO with the following methods:


public interface CompactDiscDAO 
	
void addCompactDisc(CompactDisc disc);
CompactDisc getCompactDiscByTitle(String title);
Collection<CompactDisc> getDiscsByArtist(String artist);
Collection<CompactDisc> getAllDiscs();
}

3.	In the same package, create an implementation of the interface called CompactDiscDAOMongo and add empty implementations of each method.
4.	Annotate the class with the spring annotation @Repository, since this is a data access class.
5.	Add a private field of type MongoTemplate and annotate that with @Autowired.
6.	Within each method, add the appropriate code to complete the functionality of the method using the mongoTemplate object.
7.	If you wish, create some unit tests to check that it works properly.
## Create the Service Layer

You will now create a sample service layer which will add CDs to the catalog and also allow users to search the catalog by title or artist. The client will be written so that it can work with the Mock or the real DAO implementation. 

1.	Create a new class called com.conygre.training.services.CompactDiscService.
2.	Annotate the class with @Service so that it gets created by Spring as a component.
3.	Add a property for the DAO which will be of type CompactDiscDAO. You will need a set method so that Spring can inject it. You can mark it as @Required if you wish to ensure that it gets injected.
4.	Add the following methods:
addCDToCatalog(CompactDisc cd)
getCatalog()
5.	In the ****addCDToCatalog** method, add it to the catalog. 
6.	In the ****getCatalog** method, retrieve all the CDs and then return them.
7.	Finally, test your service bean either in a JUnit test written in a similar pattern to the earlier test you created.





