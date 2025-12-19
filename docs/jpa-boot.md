# Creating a SpringBoot Application

## Introduction

In this exercise you will:

1. Create a SpringBoot application
2. Set up the application to use a JPA repository
3. Create a Service layer for your repository
4. Test your service layer

In later exercises, you will add in a REST API to your application.

A complete solution to this exercise can be found in [Solutions/workspace/CompactDiscSpringBoot_JPA](Solutions/workspace/CompactDiscSpringBoot_JPA).

## Part 1: Creating the SpringBoot Application

### Step 1: Create a New Maven Project in IntelliJ

1. Open **IntelliJ Community Edition**.
2. Select **File > New > Project...**
3. Choose **Maven** on the left.
4. Click **Next**.
5. Enter the following details:
    - **GroupId:** `com.conygre.spring.boot`
    - **ArtifactId:** `CompactDiscSpringBoot_JPA`
    - **Version:** (accept default)
6. Click **Next**, then **Finish**.
7. Wait for IntelliJ to finish creating and indexing the project.

### Step 2: Add Spring Boot, JPA, and MySQL Dependencies

1. Open the generated `pom.xml` file.
2. Add the following dependencies inside the `<dependencies>` section:

    ```xml
    <!-- Spring Boot Starter Parent -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.4</version>
    </parent>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- logging library -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>


    <!-- MySQL Connector -->
    <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.22</version>
	</dependency>
    ```

3. Click the **Load Maven Changes** prompt in IntelliJ (or use the Maven tool window to reload the project).

### Step 3: Create the Main Application Class

1. In `src/main/java/com/conygre/spring/boot`, create a new Java class named `AppConfig`.
2. Add the following code:

    ```java
    package com.conygre.spring.boot;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class AppConfig {
        public static void main(String[] args) {
            var context = SpringApplication.run(AppConfig.class, args);
            context.getBean(CompactDiscService.class)
                   .getCatalog()
                   .forEach(disc -> System.out.println(disc.getTitle()));
        }
    }
    ```

This code will run your SpringBoot application.

### Step 4: Configure Database Connection

1. In `src/main/resources`, open or create `application.properties`.
2. Add the following properties (update username and password as needed):

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/conygre
    spring.datasource.username=root
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

### Step 5: Run the Project to Check for Errors

1. In IntelliJ, open the `AppConfig` class.
2. Right-click anywhere in the file and select **Run 'AppConfig.main()'**.
3. Watch the console for any errors. If the application starts successfully, you should see a message like `Started AppConfig` and no exceptions.
4. If there are errors, review the console output and check your dependencies and configuration.

You have now verified that your basic Spring Boot project builds and runs without errors.

You have now created the basic SpringBoot project.

## Part 2 Adding the SpringData Repository

You will now start to add some components to your application. We will begin by copying over your entity classes from the previous exercise.

### Step 1 Copying Entity Classes from BasicHibernate

You will now add your entity classes to the new project by copying them from the previous exercise.

1. In IntelliJ, open the **Project** view (usually on the left side of the window).
2. Expand the folder `Labs/BasicHibernate/src/main/java/com/conygre/hibernate` (or wherever your `CompactDisc` and `Track` entity classes are located in the `BasicHibernate` project).
3. Select the files `CompactDisc.java` and `Track.java`.
4. Right-click and choose **Copy** (or press `Ctrl+C`).
5. In your new project (`CompactDiscSpringBoot_JPA`), expand `src/main/java/com/conygre/spring/boot`.
6. Right-click the `com.conygre.spring.boot` package, select **New > Package**, and create a new sub-package called `entities`.
7. Expand the new `entities` package.
8. Right-click the `entities` package and choose **Paste** (or press `Ctrl+V`).
9. If prompted, update the package declaration at the top of each file to `package com.conygre.spring.boot.entities;`.
10. Save the files.

You have now copied your entity classes into your new Spring Boot project.

### Step 2: Creating the JPA Repository

You will now create a repository interface to access your `CompactDisc` entities using Spring Data JPA.

1. In IntelliJ, open the **Project** view.
2. In your new project (`CompactDiscSpringBoot_JPA`), expand `src/main/java/com/conygre/spring/boot`.
3. Right-click the `com.conygre.spring.boot` package, select **New > Package**, and create a new sub-package called `repo`.
4. Right-click the `repo` package and select **New > Java Class** (or **New > Java Interface**).
5. Name the new interface `CompactDiscRepository`.
6. Add the following code to the interface:

    ```java
    package com.conygre.spring.boot.repo;

    import com.conygre.spring.boot.entities.CompactDisc;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface CompactDiscRepository extends JpaRepository<CompactDisc, Integer> {
        
    }
    ```

7. Save the file.

You have now created a JPA repository for the `CompactDisc` entity.

#### Reflection Question

**What capabilities does this empty `CompactDiscRepository` interface provide, even though it does not declare any methods?**

> Hint: Think about the methods and features inherited from the `JpaRepository` superinterface. What kinds of operations can you already perform on `CompactDisc` entities using this repository, and why? You will be using these capabilities in the next step.

You have now created the 'data layer' for your application. Most of the heavy lifting is being done by SpringBoot.

## Part 3: Introducing the Service Layer

Before we add the service layer to our application, it's important to understand its role and purpose in a typical Spring Boot architecture.

The **service layer** acts as an intermediary between the data access layer (repositories) and the presentation layer (such as controllers or REST endpoints). Its main responsibilities are:

- Encapsulating business logic: The service layer contains the core logic of your application, ensuring that business rules are applied consistently.
- Coordinating data access: It interacts with one or more repositories to retrieve, update, or delete data as needed.
- Providing a clear API: The service layer exposes methods that can be used by controllers or other components, making your application easier to maintain and test.
- Supporting transactions: By placing business logic in the service layer, you can easily manage transactions and ensure data consistency.

#### Reminder: Spring Dependency Injection and Service Design

As you create the service layer, remember that Spring uses **dependency injection** to manage and provide the components your application needs. By defining your service as an interface and providing a separate implementation class, you gain several advantages:

- **Loose coupling:** Your code depends on abstractions (interfaces) rather than concrete implementations, making it easier to change or replace components.
- **Testability:** You can easily substitute mock implementations for testing purposes.
- **Flexibility:** You can provide multiple implementations of the same interface if needed.

Spring will automatically inject the correct implementation wherever the interface is required, as long as you annotate your implementation with `@Service` (or another appropriate stereotype annotation).

You will now create both a service interface and its implementation for managing `CompactDisc` entities.

### Step 1: Create the Service Layer Interface

You will now create a service interface to define the operations for managing `CompactDisc` entities.

1. In IntelliJ, open the **Project** view.
2. In your new project (`CompactDiscSpringBoot_JPA`), expand `src/main/java/com/conygre/spring/boot`.
3. Right-click the `com.conygre.spring.boot` package, select **New > Package**, and create a new sub-package called `services`.
4. Right-click the `services` package and select **New > Java Interface**.
5. Name the new interface `CompactDiscService`.
6. Add the following methods to the interface. 

    ```java
    package com.conygre.spring.boot.services;

    import com.conygre.spring.boot.entities.CompactDisc;
    import java.util.Collection;

    public interface CompactDiscService {
        Collection<CompactDisc> getCatalog();
        CompactDisc getCompactDiscById(int id);
        CompactDisc getCompactDiscByTitle(String title)
        CompactDisc addNewCompactDisc(CompactDisc disc);
        void deleteCompactDisc(int id);
        void deleteCompactDisc(CompactDisc disc);
        CompactDisc updateCompactDisc(CompactDisc disc);
    }
    ```

7. Save the file.

You have now defined the service interface for your application, which will be implemented in the next step.

### Step 2: Implement the Service Class Using IntelliJ Features

You will now create a class that implements the `CompactDiscService` interface. IntelliJ can help you generate the method stubs automatically.

1. In IntelliJ, open the **Project** view.
2. In your project, expand `src/main/java/com/conygre/spring/boot/services`.
3. Right-click the `services` package and select **New > Java Class**.
4. Name the new class `CompactDiscServiceImpl`.
5. In the new class file, declare that it implements `CompactDiscService`:

    ```java
    public class CompactDiscServiceImpl implements CompactDiscService {
        // ...
    }
    ```

6. Place your cursor on the class name, then press `Alt+Enter` (or use the lightbulb icon) and select **Implement methods**. IntelliJ will show a dialog with all the methods from the interface.
7. Select all methods and click **OK**. IntelliJ will generate method stubs for you.
8. Add a private field for the **CompactDiscRepository**, and annotate with **@Autowired**. That will ensure that the implementation of the CompactDiscRepository gets injected for you.
9. Annotate the class using **@Service**, which will ensure that Spring instantiates this class for you.

    ```java
    @Service
    public class CompactDiscServiceImpl implements CompactDiscService {
        private final CompactDiscRepository repo;

        @Autowired
        public CompactDiscServiceImpl(CompactDiscRepository repo) {
            this.repo = repo;
        }
        // ...existing code...
    }
    ```

10. Implement the `getCatalog` method. Which repository method will you use to get all of the CompactDisc entity objects?

    ```java
    @Override
    public Collection<CompactDisc> getCatalog() {
        // add a suitable call to the repository here
    }
    ```

11. Leave the other methods as generated (they can return `null` or have empty bodies for now).
12. Save the file.

You have now used IntelliJ's features to quickly implement the service class, focusing on the `getCatalog` method. The other methods can be completed in later steps.

## Part 4: Check if the Application Works

Later you will be introduced to how we can write automated tests for our Spring Boot applications. For now, we will just test our Service layer by invoking the `getCatalog` function from our main method.

### Testing the Service Layer from the Main Method

1. Open your `AppConfig` class (the main application class).
2. In the `main` method, after starting the Spring application, retrieve the `CompactDiscService` bean from the application context. You can do this by calling `context.getBean(CompactDiscService.class)`.
3. Call the `getCatalog()` method on the service and print out the titles of the compact discs to the console. This will confirm that your service and repository layers are working and that you can access your data.

Here is an example of what your `main` method might look like:

```java
public static void main(String[] args) {
    var context = SpringApplication.run(AppConfig.class, args);
    context.getBean(CompactDiscService.class)
           .getCatalog()
           .forEach(disc -> System.out.println(disc.getTitle()));
}
```

**Explanation:**
- `SpringApplication.run(AppConfig.class, args)` starts the Spring Boot application and returns the application context.
- `context.getBean(CompactDiscService.class)` retrieves the service bean from the context.
- `getCatalog()` fetches all compact discs from the database.
- The `forEach` loop prints the title of each disc, confirming that the service and repository are working together and that your application is correctly wired.

Run your application. If everything is set up correctly, you should see the titles of the compact discs printed in the console. This is a simple way to verify that your service layer is functioning before moving on to more advanced testing or adding a REST API.







