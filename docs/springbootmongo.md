# Spring Boot with Mongo
## Aims
In this exercise you will see how easy it is to build a complete end to end application using 
much of the technology you have covered in the previous chapters, but with much of the code and configuration no longer required.

## Part 1 Create a Spring Boot Application
1.	Create a new Maven project with an artifact ID MySpringBoot and a group ID of com.conygre.spring.boot.
2.	Add the following to your pom file after the version element:

```
<properties>
		<java.version>1.8</java.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<start-class>com.conygre.training.MongoJavaConfig</start-class>
	</properties>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.4.1.RELEASE</version>
	</parent>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
		<!-- Swagger support -->
		 <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.2.2</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.2.2</version>
            <scope>compile</scope>
        </dependency>
	</dependencies>
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
1.	Begin by copying the `CompactDisc` entity class into your project.
2.	Copy in your Spring Data Repository interface from the Spring Data exercise.
3.	Copy over your `CompactDiscService` class from your Spring Data exercise, and define an interface for it.
4.	Copy over your REST API controller class.
5.	Finally, create an application class called `com.conygre.training.MongoJavaConfig` and annotate it with the following Spring Boot annotations:
 
 ```
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.conygre.training.data")
```

6.	Add a `main` method to the class and in main, run the `SpringApplication.run(MongoJavaConfig.class, args)` method.
7.	That’s now completed the application, so make sure that any Tomcat servers are no longer running, and then run the `MongoJavaConfig` class.
8.	Visit your new REST API in the browser and it will return your compact discs. The entire REST API should be working. 
9.	If you have not completed all of the CRUD methods you could complete them now. Remember to restart the Spring Boot application each time you change any settings.


