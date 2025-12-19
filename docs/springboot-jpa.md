# Spring Boot with JPA

## Aims

In this exercise, you will build on your previous project, and add a REST API into your CompactDisc application. You currently have the data access layer and service layer. You will now add in the REST API layer.

The complete solution to this exercise can be found in `Solutions/workspace/CompactDiscDaoWithRestAndBoot`.


## Part 1 Add the spring-web dependency

1.	Open your project pom.xml file and add the following dependency:
```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
```



## Part 2 Create a REST Controller


1.	Using a new package com.conygre.spring.boot.rest, create a REST class annotated as shown with an implementation of the CompactDiscService injected.

```java
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
2.	That should be enough to get the API to work correctly, so try running your AppConfig class.

3.	Visit your new REST API in the browser and it will return your compact discs. 

The URL will be: http://localhost:8080/api/compactdiscs

Congratulations! You have now created the bare bones of a REST API. You have one method implemented to return all of the entities. 

You will now complete the rest of the application so that it supports create/read/update/delete. 

## Part 3: Extend the Application with Additional REST Methods

Now extend your REST API to support the following operations:

1. **Get a CompactDisc by ID**
   - Add a method to your controller to handle GET requests for a specific ID.
   - Call the appropriate service method to retrieve a CompactDisc by its ID.

2. **Add a new CompactDisc**
   - Add a method to handle POST requests.
   - Use the service layer to add a new CompactDisc.

3. **Delete a CompactDisc by ID**
   - Add a method to handle DELETE requests for a specific ID.
   - Use the service layer to delete a CompactDisc by its ID.

4. **Update a CompactDisc**
   - Add a method to handle PUT requests.
   - Use the service layer to update an existing CompactDisc.

5. **(Extension) Find by Title**
   - Add a method to your controller to find a CompactDisc by its title.
   - You will need to add a custom method in your JPA repository for this (e.g., `findByTitle`).

> Try to implement these methods yourself using what you have learned so far. Refer to the existing `findAll` method for guidance on how to structure your controller methods and service calls.

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

Using IntelliJ Ultimate, you can run these .rest files by simply clicking the play button that appears above each request, since IntelliJ Ultimate natively recognizes and supports HTTP request files. This allows you to easily send HTTP requests and view responses directly within the IDE, making it convenient to test your API endpoints without leaving your development environment.

If you don't have IntelliJ Ultimate, you can use Visual Studio Code as an alternative. In VSCode, install the REST Client extension from the Extensions marketplace. Once installed, you can open .rest files, and each request will have a 'Send Request' link above it. Clicking this link will execute the HTTP request and display the response in a side panel. This provides a similar experience to IntelliJ Ultimate and is a great free option for testing your REST APIs interactively.

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

2. Now you can add in the `log4j2.xml` file to your `src/main/resources` folder. You can use the solution project file for that. This XML file allows for more advanced and flexible logging configuration compared to the properties file.

3. To add some log messages, we could start with your controller. So open the `CompactDiscController` class and add the following instance variable:

```
private static Logger logger = LogManager.getLogger(CompactDiscController.class);
```

This logger object can then be used to write log messages.

4. In your `findAll()` method, add the following logging line of code:

```
logger.info("managed to call a Get request for findAll");
```

This message is an info message and will only appear if your configuration file has an entry specifying that you wish to see info messages for this logger.

5. So finally, open `src/main/resources/log4j2.xml` and locate the logger configuration for your package (e.g., `com.conygre.spring.boot`). Set the logging level to `info` to see info messages, or to `error` to only see error messages.

For example, in `log4j2.xml`:

```xml
<Logger name="com.conygre.spring.boot" level="info" additivity="false">
    <AppenderRef ref="Console"/>
</Logger>
```

6. To test this out, run your Spring Boot application and visit the http://localhost:8080/api/compactdiscs in a browser. Once you have seen the list of CDs, return to the application console and you will see your log message.

7. Now terminate the application, and in `log4j2.xml`, change the log level to `error`:

```xml
<Logger name="com.conygre.spring.boot" level="error" additivity="false">
    <AppenderRef ref="Console"/>
</Logger>
```

8. Relaunch the application and visit the same URL in the browser. Return to the console and your message will no longer be there because you are now only set to see error messages and higher.

The log4j2 message levels can be reviewed here: https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm.


## Part 5 Adding Swagger Support with SpringDoc OpenAPI

Finally, we can add Swagger support to our API. Modern Spring Boot 3.x applications use **springdoc-openapi**. 

1. Open the `pom.xml` file and add the following dependency:

```xml
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	<version>2.0.4</version>
</dependency>
```

2. That's it! No additional configuration classes are required. SpringDoc will automatically scan your REST controllers and generate the OpenAPI documentation for you.

3. Restart or start the application, and to view the Swagger UI, use the following URL:

http://localhost:8080/swagger-ui.html

You can also access the raw OpenAPI specification at:

http://localhost:8080/v3/api-docs

4. Follow the link and try out some of your methods. The REST API should all still be working as before, and you can now test it out using the Swagger interface.

> **Note:** If you want to customize the API documentation (e.g., add a title, description, or contact info), you can add properties to your `application.properties` file:
>
> ```properties
> springdoc.api-docs.path=/v3/api-docs
> springdoc.swagger-ui.path=/swagger-ui.html
> ```
>
> Or, create a configuration bean to customize the OpenAPI object if you need more advanced customization.
>
> For more information on how to customize the OpenAPI object and SpringDoc configuration, see the official SpringDoc documentation:  
> [SpringDoc OpenAPI - Customizing the OpenAPI Object](https://springdoc.org/#how-can-i-customise-the-openapi-object)