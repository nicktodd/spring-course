# Spring Boot with JPA

## Aims

In this exercise you will see how easy it is to build a complete end to end application using much of the technology you have covered in the previous chapters, but with much of the code and configuration no longer required.


## Part 1 Create a Spring Boot Application
1.	Create a new Maven project with an artifact ID MySpringBoot and a group ID of com.conygre.spring.boot.

2.  After the `version` closing element, add the following `properties` section:

```
<properties>
		<java.version>11</java.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<start-class>com.conygre.spring.boot.AppConfig</start-class>
</properties>
```

The java.version specifies your Java version, the source encoding is the UTF encoding used for the .java files, and the start class is used if you zip everything up as a jar and try to run the Jar file. It will then know which actual class to run.


3.	Now add the following parent pom entry:

```
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.5.3</version>
</parent>
```

4.	Now add the following 3 dependencies:
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

4.	Finally, after the dependencies section, add the spring boot plugin:
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

All of this could have been done using the Spring Initializer, but doing it manually like this demonstrates that really all the Spring Initializer does it put all these things into your POM or Gradle file. If you are working in a corporation like a large bank or an insurance company, you will most probably not be able to use the Spring Initializer anyway.


## Part 2 Create and Include the Required Classes

We can reuse a lot of the classes we have already written, so we will begin by porting some of them over.

1.	Begin by copying the CompactDisc and Track entity classes into your project.

2.	Copy in your Spring Data Repository interface from the Spring Data exercise.

3.	Copy over your CompactDiscService class from your Spring Data exercise, and define an interface for it - renaming the class ```CompactDiscServiceImpl```, with an interface called ```CompactDiscService```.

```
public interface CompactDiscService {
	List<CompactDisc> getCatalog();
}


@Service
@Transactional
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

5.	Finally, create an application class called AppConfig and place it in the package above all your other sub-packages, ie. something like com.conygre.spring. Annotate it with the following Spring Boot annotations:

```
@SpringBootApplication
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

When you get to your POST, PUT and DELETE methods, one of the challenges will be how to test them. For this you can use simple test files with the extension .rest. They take the following form.

```
HTTPMETHOD URL
ANY HEADERS

Body of request
```

Here are some examples, you can use these to get you going. 


This example posts a new Compact disc into the database for you:
```
POST http://localhost:8080/api/compactdiscs
Content-type: application/json

{
    "title" : "Sweet Caroline",
    "artist" : "Neil Diamond",
    "price" : "13.99",
    "tracks" : "1"
}
```

Below is a request to delete CD number 14
```
DELETE http://localhost:8080/api/compactdiscs/14
```

Using IntelliJ ultimate you can run these files by simply clicking the play button, since IntelliJ Ultimate recognises them. If you don't have IntelliJ ultimate, you can run them from Visual Studio Code. In VSCode, install the REST Client plugin, and you can open them in VSCode and run them from there.

## Part 4 Adding Logging Support

Logging is important for any enterprise application, so now you will add logging support to your project. 

1. Open the pom.xml and add into the dependency list the following dependency.

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

This is for the log4j Logging libraries. 


2. We also need to modify the web starter dependency as in some versions of spring boot there is a clash of versions. So to avoid that, modify your spring-starter-web dependency to look like this:

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>
```

3. Now you can add in the log4j.properties file to your src/main/resources folder. You can use the solution project file for that.

4. To add some log messages, we could start with your controller. So open the `CompactDiscController` class and add the following instance variable:

```
private static Logger logger = LogManager.getLogger(CompactDiscController.class);
```

This logger object can then be used to write log messages.

5. In your findAll() method, add the following logging line of code:

```
logger.info("managed to call a Get request for findAll");
```

This message is an info message and will only appear if your configuration file has an entry specifying that you wish to see info messages for this logger.

6. So finally, open `src/main/resources/log4j.properties` and locate the following entry in the file:

```
log4j.logger.com.conygre.spring=info
```

This line is setting the logging level for any logs from classes in the package com.conygre.spring to be output at the level of info and above.

7. To test this out, run your Spring Boot application and visit the http://localhost:8080/api/compactdiscs in a browser. Once you have seen the list of CDs, return to the application console and you will see your log message.

8. Now terminate the application, and in log4j.properties, change the log level to `error`.

```
log4j.logger.com.conygre.spring=error
```


9. Now relaunch the application again and visit the same URL in the browser. Return to the console and your message will no longer be there because you are now only set to see error messages and higher.

The log4j message levels can be reviewed here: https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm.


## Part 5 Adding Swagger Support

Finally we can add Swagger support to our API. To add Swagger we will need some additional dependencies and then configure some additional beans.

1. Open the `pom.xml` file and add the following two dependencies:

```
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.9.2</version>
	<scope>compile</scope>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.9.2</version>
	<scope>compile</scope>
</dependency>
```
2. Now create a new class called `com.conygre.spring.boot.SwaggerConfig`.

3. Annotate the class using the following annotations:

```
@EnableSwagger2
@Profile("!test") 
```

The profile is required for some tests we will be doing later, but it would be easier to put it in whilst we are at it. The issue is that Swagger has a habit of breaking certain types of test. It is a well known problem!

4. Within the class, add the following bean configuration methods:

```
@Bean
public Docket newsApi() {
	return new Docket(DocumentationType.SWAGGER_2)
			.groupName("compactdiscs")
			.apiInfo(apiInfo())
			.select()
			.paths(PathSelectors.any())
			.build();
}

private ApiInfo apiInfo() {
	return new ApiInfoBuilder()
			.title("Album REST API with Swagger")
			.description("This API allows you to interact with albums. It is a CRUD API")
			//.termsOfServiceUrl("http://www.conygre.com")
			.contact(new Contact("Nick Todd", "http://www.conygre.com", "nick.todd@conygre.com"))
			//.license("Apache License Version 2.0")
			//.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
			//.version("2.0")
			.build();
}
```

5. Finally, we need to enable this configuration in our `AppConfig` class, so open your `AppConfig` class and add the following annotation:

```
@Import(SwaggerConfig.class)
```

Creating separate config classes like this makes it much easier to turn configuration elements on and off.

6. You have now added Swagger, so restart or start the application, and to test the Swagger, use the following URL:

http://localhost:8080/swagger-ui.html


Follow the link and try out some of your methods. The REST API should all still be working as before and you can now test it out using the Swagger interface.