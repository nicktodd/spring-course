# Adding Some Tests
## Aims

In this exercise you will add some tests to your project. The tests will be a mixture of unit tests, integration tests, and some functional tests.

## Locate a Project
You will need a project to add some tests into. A good starting point would be any project you have that has a working REST API with a database at the back end. To begin with, we suggest you make a copy of the the solution project CompactDiscDaoWithRestAndBoot, and delete everything in the src/test/java and src/test/resources folders. That can then be your starting point, and the original project has the solution code in it!

Open your new project in your preferred IDE such as IntelliJ.


## Part 1 Create some Unit Tests

First we will create some unit tests. We will test our controller class. Now the controller requires a Service layer which also requires a Repository layer. We don't want to test those two layers so they need to be mocked.

1. In `src/test/java`, create a new class called `com.conygre.spring.boot.controller.TestCompactDiscControllerUnitTest`.

2. Annotate the class with the `@RunWith(SpringRunnner.class)` annotation. This tells the test engine to use the SpringRunner which allows for alternative test configurations.

3. Inside the class, now create a nested static class. You may not have seen this before, but it is possible to nest a class inside another class. We will be nesting a configuration class inside our test class which can then be used as a Java Spring configuration which will replace the standard configuration of the application. We need this to create our mock beans. The code should look like this:

```
@TestConfiguration
protected static class Config {

}
```

4. Within the class, you will be adding beans as you would in a standard Spring configuration class. So add the following bean that will mock the CompactDiscRepository.

```
 @Bean
 @Primary
 public CompactDiscRepository repo() {
   return mock(CompactDiscRepository.class);
 }
```

The mock() method instructs Mockito to create a mock implementation of the repository.

5. Now add another bean but this time we will mock the Service layer. This needs a bit more work, as we need to configure this mock service layer to return some data if it is asked for it. So add the following bean:

```
@Bean
@Primary
public CompactDiscService service() {
    CompactDisc cd = new CompactDisc();
    List<CompactDisc> cds = new ArrayList<>();
    cds.add(cd);

    CompactDiscService service = mock(CompactDiscService.class);
    when(service.getCatalog()).thenReturn(cds);
    when(service.getCompactDiscById(1)).thenReturn(cd);
    return service;
}
```

If you review the content of this function, we are configuring the service class to return a list of one empty CD if asked, and if asked for a CD by ID, then just return the empty CD. This could be changed to return more data, but for now this will be sufficient.

6. Now add another bean which will be the controller. We are not mocking this as this is the class we actually want to test! So add the following bean:

```
@Bean
@Primary
public CompactDiscController controller() {
    return new CompactDiscController();
}
```

That is the configuration class complete, so now we can autowire the controller into the test itself, so outside of the static class declare the following variable in the test class.

```
@Autowired
private CompactDiscController controller;
```

Now we are ready to add some tests to test the controller.

7. Add the following test:

```
@Test
public void testFindAll() {
    Iterable<CompactDisc> cds = controller.findAll();
    Stream<CompactDisc> stream = StreamSupport.stream(cds.spliterator(), false);
    assertThat(stream.count(), equalTo(1L));
}
```

This test will confirm that the discs coming back from the controller has a count of 1. we added one disc and there should only be 1 disk in the response.

8. To run the test, click on the green arrow next to the test in your IDE (both IntelliJ and VisualStudio Code will have this).

9. Now add another test to check that we get one CD back if we ask for CD number 1.

```
@Test
public void testCdById() {
    CompactDisc cd = controller.getCdById(1);
    assertNotNull(cd);
}
```

10. Run this test in the same way that you ran the previous test.

11. Unfortunately, the configuration class will interfere with our later tests, so add the @Ignore to the top of each your tests, and then comment out the three `@Bean` and `@Primary` annotations in the config class.


## Part 2: Add some Integration Tests

Now we will test our service and repo layer integration. Does the service layer correctly integrate with the repository layer?

1. Create another test class called `com.conygre.spring.boot.repo.TestCDRepository`.

2. Add the following code annotations at the top of the class:

```
@RunWith(SpringRunner.class)
@DataJpaTest // use an in memory database
@SpringBootTest(classes={com.conygre.spring.boot.AppConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests

```

These annotations will set up an in memory database since we don't want to test with the actual database - remember we are only testing the integration between the two classes. We then specify the config class which is our spring boot application config, and the finally we specify an alternative application.properties files to use. This is stop swagger breaking our tests!

3. Now we can inject a special three beans into our test:
   1. A spring class called a `TestEntityManager`. We need this to put some test data into our project.
   2. The `CompactDiscRepository` so we can test it works with the service layer
   3. The `CompactDiscService` object which will have the repository injected which will in turn use the in memory database
   4. The `CompactDiscController`. We can also test this class if we want to.

```
    @Autowired
    private TestEntityManager manager;


    @Autowired // this is a mock which is injected because of the @DataJpaTest
    private CompactDiscRepository repo;

    @Autowired
    CompactDiscService discService;


    @Autowired
    CompactDiscController controller;
```

4. To set up the database for our tests, we can use an `@Before` method which will insert a row into our in memory database. The returned primary key we can then put into a variable so it can be checked by our tests when retrieving by ID.

```
private int discId;

@Before
public  void setupDatabaseEntryForReadOnlyTests() {
    CompactDisc disc = new CompactDisc("Abba Gold", 12.99, "Abba", 5);
    CompactDisc result = manager.persist(disc);
    discId = result.getId();

}
```

You are now able to add your tests, as you add each test, ensure that it passes before moving on.

5. Now everything is set, and we can now test our application. First, let's test that our service layer can successfully retrieve a CompactDisc by ID.

```
@Test
public void canRetrieveCDByArtist() {
    Iterable<CompactDisc> discs = repo.findByArtist("Abba");
    Stream<CompactDisc> stream = StreamSupport.stream(discs.spliterator(), false);
    assertThat(stream.count(), equalTo(1L));
}
```

6. Now let's test that we can retrieve all of the CDs from the database:

```
@Test
public void compactDiscServiceCanReturnACatalog() {
    Iterable<CompactDisc> discs = discService.getCatalog();
    Stream<CompactDisc> stream = StreamSupport.stream(discs.spliterator(), false);
    Optional<CompactDisc> firstDisc = stream.findFirst();
    assertThat(firstDisc.get().getArtist(), equalTo("Abba"));
}
```

7. Finally, let's see if the controller is successfully interacting with the service layer.

```
@Test
public void controllerCanReturnCDById() {
    CompactDisc cd = controller.getCdById(discId);
    assertThat(cd.getArtist(), equalTo("Abba"));
}
```


## Part 3: Create some Functional Tests

Finally let's create some functional test using RestTemplate.

1. Create a class called `functional.tests.CompactDiscRestTests` and add the following code:

2. Within the class, instantiate a property of type `RestTemplate`.

```
private RestTemplate template = new RestTemplate();
```

3. The `RestTemplate` is quite easy to use, so let's try it out retrieving all the CDs in the catalog by adding the following test.

```
@Test
public void testFindAll() {
    List<CompactDisc> cds = template.getForObject("http://localhost:8080/api/compactdiscs", List.class);
    assertThat(cds.size(),  greaterThan(1));
}
```
4. You cannot run this test until you start your actual application, since remember this test is testing the working application, so launch your application as normal.

5. Run the test, and it should pass.

6. Now let's add an additional test to retrieve a specific CD. 

```
@Test
public void testCdById() {
    CompactDisc cd = template.getForObject
            ("http://localhost:8080/api/compactdiscs/16", CompactDisc.class);
    assertThat(cd.getArtist(), equalTo("Spice Girls"));
}
```
7. You can run this test in the same way, making sure your server is still running.
