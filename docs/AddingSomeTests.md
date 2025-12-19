# Adding Some Tests
## Aims

In this exercise you will add some tests to your project. The tests will be a mixture of unit tests, integration tests, and some functional tests.

You will be adding tests to the project that you created in the previous exercise.

## Preparation: Review / Add in the Dependencies

1. Open your project pom.xml file and add in / review the following dependency. This is the standard SpringBoot testing framework libraries are added into your project.

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <scope>test</scope>
</dependency>
```

2. Now locate / add in the following dependency. This dependency gives us an in memory database. We can use this when completing integration testing to ensure our system can work correctly with a database.

```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>test</scope>
  <version>1.4.200</version>
</dependency>
```

3. Now add the following build plugin. This is used to run the tests with Maven and will also help integrate the results appearing in IntelliJ.

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.2.5</version>
</plugin>
```


Now we are ready to write some tests!!


## Part 1 Create some Unit Tests

First we will create some unit tests. We will test our controller class. Now the controller requires a Service layer which also requires a Repository layer. We don't want to test those two layers so they need to be mocked.

[!WARNING]  
When your IDE prompts you regarding imports for JUnit and Hamcrest, make sure you choose org.junit.jupiter.* and org.hamcrest.*. Do NOT choose org.junit as this is the JUnit 4 libraries and will not work correctly.


1. In `src/test/java`, create a new class called `com.conygre.spring.boot.controller.TestCompactDiscControllerUnitTest`.

2. Annotate the class with the `@ExtendWith(SpringExtension.class)` annotation. This tells the test engine to use the SpringExtension. There are multiple extension options that can be used here. This is the Spring one.

3. Inside the class, now create a nested static class. You may not have seen this before, but it is possible to nest a class inside another class. We will be nesting a configuration class inside our test class which can then be used as a Java Spring configuration which will replace the standard configuration of the application. We need this to create our mock beans. The code should look like this:

```java
protected static class Config {

}
```

4. Now add the `@ContextConfiguration` annotation to the test class to tell Spring to use our nested Config class:

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestCompactDiscControllerUnitTest.Config.class)
public class TestCompactDiscControllerUnitTest {
```

5. Within the Config class, we will mock the Service layer. This needs a bit more work, as we need to configure this mock service layer to return some data if it is asked for it. So add the following bean:

```java
@Bean
public CompactDiscService service() {
    CompactDisc cd = new CompactDisc();
    cd.setTitle("Harry's House");
    cd.setArtist("Harry Styles");
    List<CompactDisc> cds = new ArrayList<>();
    cds.add(cd);

    CompactDiscService service = mock(CompactDiscService.class);
    when(service.getCatalog()).thenReturn(cds);
    when(service.getCompactDiscById(1)).thenReturn(cd);
    return service;
}
```

If you review the content of this function, we are configuring the service class to return a list of one CD if asked, and if asked for a CD by ID, then just return the CD. This could be changed to return more data, but for now this will be sufficient.

6. Now add another bean which will be the controller. We are not mocking this as this is the class we actually want to test! So add the following bean:

```java
@Bean
public CompactDiscController controller() {
    return new CompactDiscController();
}
```

That is the configuration class complete, so now we can autowire the controller into the test itself, so outside of the static class declare the following variable in the test class.

```java
@Autowired
private CompactDiscController controller;
```

Now we are ready to add some tests to test the controller.

7. Add the following test:

```java
@Test
public void testFindAll() {
    Iterable<CompactDisc> cds = controller.findAll();
    Stream<CompactDisc> stream = StreamSupport.stream(cds.spliterator(), false);
    assertThat(stream.count(), equalTo(1L));
}
```

This test will confirm that the discs coming back from the controller has a count of 1. we added one disc and there should only be 1 disk in the response.

8. To run the test, click on the green arrow next to the test in your IDE (both IntelliJ and VisualStudio Code will have this).

9. Now add another test to check that we get one CD back if we ask for CD number 1. Note that you may have named your Controller method with a slightly different name.

```java
@Test
public void testCdById() {
    CompactDisc cd = controller.getCdById(1);
    assertNotNull(cd);
}
```

10. In IntelliJ IDEA, you can run the test by clicking the green arrow icon next to the test method or class name in the editor gutter. Alternatively, right-click anywhere inside the test method or class and select **Run 'testMethodName()'** or **Run 'TestCompactDiscControllerUnitTest'**. The test results will appear in the Run or Test panel at the bottom of the IDE.

Once it is passing, move on to the integration tests.


## Part 2: Add some Integration Tests

Now we will test our service and repo layer integration. Does the service layer correctly integrate with the repository layer?

1. Create another test class called `com.conygre.spring.boot.repo.TestCDRepository`.

2. Add the following annotations at the top of the class:

```java
@ExtendWith(SpringExtension.class)
@DataJpaTest // use an in memory database
@ContextConfiguration(classes=AppConfig.class)
```

These annotations will set up an in-memory database (H2) since we don't want to test with the actual database - remember we are only testing the integration between the classes. We then specify the config class which is our Spring Boot application config.

3. Now we can inject four beans into our test:
   1. A Spring class called a `TestEntityManager`. We need this to put some test data into our in-memory database.
   2. The `CompactDiscRepository` - this will be automatically configured by `@DataJpaTest` to work with the in-memory H2 database.
   3. The `CompactDiscService` object which will have the repository injected and will use the in-memory database.
   4. The `CompactDiscController` - we can also test this class if we want to.

```java
@Autowired
private TestEntityManager manager;

@Autowired // this is injected because of the @DataJpaTest
private CompactDiscRepository repo;

@Autowired
private CompactDiscService discService;

@Autowired
CompactDiscController controller;
```

4. To set up the database for our tests, we can use a `@BeforeEach` annotation on a method which will insert a row into our in-memory database. The returned primary key we can then put into a variable so it can be checked by our tests when retrieving by ID.

```java
private int discId;

@BeforeEach
public void setupDatabaseEntryForReadOnlyTests() {
    CompactDisc disc = new CompactDisc("Abba Gold", 12.99, "Abba", 5);
    CompactDisc result = manager.persist(disc);
    discId = result.getId();
}
```

5. Before you run the test there is one additional task, which is to go to the `com.conygre.spring.boot.AppConfig` class, and add the `@ComponentScan` annotation. This is required so that your tests create your components from the @Service/@RestController annotations.

You are now able to add your tests. As you add each test, ensure that it passes before moving on.

6. Now everything is set, and we can test our application. First, let's test that our repository can successfully retrieve a CompactDisc by artist.

```java
@Test
public void canRetrieveCDByArtist() {
    Iterable<CompactDisc> discs = repo.findByArtist("Abba");
    Stream<CompactDisc> stream = StreamSupport.stream(discs.spliterator(), false);
    assertThat(stream.count(), equalTo(1L));
}
```

7. Now let's test that the service layer can retrieve the CDs from the database:

```java
@Test
public void compactDiscServiceCanReturnACatalog() {
    Iterable<CompactDisc> discs = discService.getCatalog();
    Stream<CompactDisc> stream = StreamSupport.stream(discs.spliterator(), false);
    Optional<CompactDisc> firstDisc = stream.findFirst();
    assertThat(firstDisc.get().getArtist(), equalTo("Abba"));
}
```

8. Finally, let's see if the controller is successfully interacting with the service layer.

```java
@Test
public void controllerCanReturnCDById() {
    CompactDisc cd = controller.getCdById(discId);
    assertThat(cd.getArtist(), equalTo("Abba"));
}
```


## Part 3: Create some Functional Tests

Finally let's create some functional tests using RestTemplate. These tests require the application to be running, so they are typically disabled for automated test runs and run manually when needed.

1. Create a class called `functional.tests.CompactDiscRestTests`.

2. Within the class, instantiate a property of type `RestTemplate`.

```java
private RestTemplate template = new RestTemplate();
```

3. The `RestTemplate` is quite easy to use, so let's try it out retrieving all the CDs in the catalog by adding the following test. Note the `@Disabled` annotation - this prevents the test from running during automated builds since it requires the server to be running.

```java
@Disabled
@Test
public void testFindAll() {
    List<CompactDisc> cds = template.getForObject("http://localhost:8080/api/compactdiscs", List.class);
    assertThat(cds.size(), greaterThan(1));
}
```

4. You cannot run this test until you start your actual application, since this test is testing the working application. So launch your application as normal first.

5. To run the test, you will need to temporarily remove or comment out the `@Disabled` annotation, then run the test. It should pass if your server is running.

6. Now let's add an additional test to retrieve a specific CD. 

```java
@Disabled
@Test
public void testCdById() {
    CompactDisc cd = template.getForObject
            ("http://localhost:8080/api/compactdiscs/16", CompactDisc.class);
    assertThat(cd.getArtist(), equalTo("Spice Girls"));
}
```

7. You can run this test in the same way, making sure your server is still running and removing the `@Disabled` annotation temporarily.

> **Note:** The `@Disabled` annotation ensures these functional tests don't fail during automated builds (e.g., `mvn test`), since they require the application to be running externally.
