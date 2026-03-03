# Testing a REST API with Cucumber and REST Assured

## Aims

In this exercise you will create a brand-new Maven project that contains a suite of **behaviour-driven** acceptance tests for the CompactDisc REST API. You will use:

- **Cucumber** to express tests as human-readable Gherkin scenarios
- **REST Assured** to make HTTP calls to the running API and assert the responses
- **JUnit 5** as the test engine that ties everything together

The API you will be testing is the one created in the `CompactDiscDaoWithRestAndBoot` project, running at `http://localhost:8080/api/compactdiscs`.

A complete solution to this exercise can be found in `Solutions/workspace/CucumberRestAssured`.

> **Before you start** – make sure the `CompactDiscDaoWithRestAndBoot` application is running. You should be able to visit `http://localhost:8080/api/compactdiscs` in a browser and see a JSON array of compact discs.

---

## Part 1: Create the Maven Project and Add Dependencies

### Step 1: Create a New Maven Project

1. In your IDE, create a new **Maven** project with the following co-ordinates:
   - **GroupId:** `com.conygre.spring.boot`
   - **ArtifactId:** `CucumberRestAssured`
   - **Version:** `1.0-SNAPSHOT`

2. Open the generated `pom.xml` and add the following `<properties>` block so that version numbers are easy to manage in one place:

```xml
<properties>
    <java.version>21</java.version>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <cucumber.version>7.15.0</cucumber.version>
    <rest-assured.version>5.4.0</rest-assured.version>
    <junit.version>5.10.2</junit.version>
</properties>
```

### Step 2: Add the Cucumber Dependencies

Add the two Cucumber libraries inside `<dependencies>`. Both are test-scoped because we only need them when running tests, not at runtime.

```xml
<!-- Cucumber core library (Java step definitions) -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>

<!-- Cucumber JUnit 5 engine -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit-platform-engine</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>
```

### Step 3: Add JUnit 5 Dependencies

Cucumber's JUnit Platform engine needs JUnit 5 and the JUnit Platform Suite libraries to be present:

```xml
<!-- JUnit 5 -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>${junit.version}</version>
    <scope>test</scope>
</dependency>

<!-- JUnit Platform Suite (needed to wire the runner class to Cucumber) -->
<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-suite</artifactId>
    <version>1.10.2</version>
    <scope>test</scope>
</dependency>
```

### Step 4: Add REST Assured and Supporting Libraries

REST Assured is the library that lets us make HTTP calls and assert the responses in a fluent, readable style. Hamcrest and Jackson are pulled in alongside it.

```xml
<!-- REST Assured – HTTP client and assertion library -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>${rest-assured.version}</version>
    <scope>test</scope>
</dependency>

<!-- Hamcrest matchers -->
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest</artifactId>
    <version>2.2</version>
    <scope>test</scope>
</dependency>

<!-- Jackson – used to parse JSON responses in step definitions -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.16.1</version>
    <scope>test</scope>
</dependency>
```

### Step 5: Configure the Maven Surefire Plugin

By default, Surefire looks for classes ending in `Test`. Our runner class is named `CucumberRunner`, so we need to tell Surefire to include it:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.5</version>
            <configuration>
                <includes>
                    <include>**/*Runner.java</include>
                </includes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Reload Maven to download the new dependencies before moving on.

---

## Part 2: Write the Gherkin Feature File

Gherkin is the plain-English language used to describe test scenarios. Each scenario maps directly to a test case.

### Step 1: Create the Feature File

1. Inside `src/test/resources`, create the directory `features`.
2. Inside `features`, create a new file called `compactdiscs.feature`.

### Step 2: Add a Background

A `Background` section runs before every scenario in the file. We will use it to set the base URL for the API, so we don't have to repeat it in every scenario:

```gherkin
Feature: Compact Disc REST API

  Background:
    Given the compact disc API is running at "http://localhost:8080"
```

### Step 3: Add a Scenario to Retrieve All Compact Discs

Add the following scenario below the Background block. It verifies that when we call the endpoint we get a non-empty list of discs, and that every disc has a title and an artist:

```gherkin
  Scenario: Retrieve all compact discs
    When I send a GET request to "/api/compactdiscs"
    Then the response status code should be 200
    And the response should contain a list of compact discs
    And each compact disc should have a title and an artist
```

### Step 4: Add a Scenario to Retrieve a Single Compact Disc by ID

Now add a scenario that retrieves a specific disc. Choose an ID that you know exists in your database (e.g. `16`):

```gherkin
  Scenario: Retrieve a compact disc by ID
    When I send a GET request to "/api/compactdiscs/16"
    Then the response status code should be 200
    And the response should contain a compact disc with an id of 16
```

### Step 5: Add a Scenario to Add a New Compact Disc

Cucumber's **DataTable** lets you pass structured data into a step. Add the following scenario to test the POST endpoint:

```gherkin
  Scenario: Add a new compact disc
    When I send a POST request to "/api/compactdiscs" with the following details:
      | title    | artist          | price | tracks |
      | Thriller | Michael Jackson | 9.99  | 9      |
    Then the response status code should be 200
```

### Step 6: Add a Scenario to Delete a Compact Disc

Finally add a scenario to test the DELETE endpoint:

```gherkin
  Scenario: Delete a compact disc by ID
    When I send a DELETE request to "/api/compactdiscs/16"
    Then the response status code should be 200
```

---

## Part 3: Create the Cucumber Runner Class

The runner class is what Maven (via Surefire) will launch. It does not contain any test logic itself — it simply points Cucumber at the feature files and the step definitions.

1. In `src/test/java`, create the package `com.conygre.spring.boot.cucumber`.
2. Inside that package, create a class called `CucumberRunner` with the following content:

```java
package com.conygre.spring.boot.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,
        value = "com.conygre.spring.boot.cucumber.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports/report.html")
public class CucumberRunner {
    // Intentionally empty – annotations do all the work
}
```

The key annotations here are:

| Annotation | Purpose |
|---|---|
| `@Suite` | Marks this as a JUnit Platform Suite |
| `@IncludeEngines("cucumber")` | Tells JUnit to use the Cucumber engine |
| `@SelectClasspathResource("features")` | Points to your `.feature` files |
| `@ConfigurationParameter(GLUE...)` | Tells Cucumber where your step definition classes live |
| `@ConfigurationParameter(PLUGIN...)` | Configures the output reporters |

---

## Part 4: Create the Step Definitions

Step definitions are the Java methods that Cucumber calls when it matches a Gherkin step. Each method is annotated with `@Given`, `@When`, `@Then`, or `@And` and a pattern matching the Gherkin text.

### Step 1: Create the Step Definitions Class

1. Create a new package `com.conygre.spring.boot.cucumber.steps` inside `src/test/java`.
2. Inside it, create a class called `CompactDiscStepDefinitions`.

### Step 2: Add the Fields and Background Step

The `Response` field holds the result of the last REST Assured call so that subsequent steps can inspect it.

```java
package com.conygre.spring.boot.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CompactDiscStepDefinitions {

    private Response response;

    @Given("the compact disc API is running at {string}")
    public void theApiIsRunningAt(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }
}
```

The `{string}` placeholder in the annotation pattern is a Cucumber expression — it matches a double-quoted string in the Gherkin step and passes it as the `baseUrl` parameter.

### Step 3: Add the GET Step

```java
@When("I send a GET request to {string}")
public void iSendAGetRequestTo(String path) {
    response = given()
            .accept("application/json")
            .when()
            .get(path);
}
```

Notice the REST Assured fluent API: `given()` sets up the request, `when()` sends it, and the result is stored in `response`.

### Step 4: Add the POST Step

The POST step receives a Cucumber `DataTable` which maps the column headers to values. We then build a JSON string and send it in the request body:

```java
@When("I send a POST request to {string} with the following details:")
public void iSendAPostRequestWithDetails(String path,
        io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    Map<String, String> data = rows.get(0);

    String json = String.format(
            "{\"title\":\"%s\",\"artist\":\"%s\",\"price\":%s,\"tracks\":%s}",
            data.get("title"),
            data.get("artist"),
            data.get("price"),
            data.get("tracks")
    );

    response = given()
            .contentType("application/json")
            .body(json)
            .when()
            .post(path);
}
```

### Step 5: Add the DELETE Step

```java
@When("I send a DELETE request to {string}")
public void iSendADeleteRequestTo(String path) {
    response = given()
            .when()
            .delete(path);
}
```

### Step 6: Add the Assertion Steps

These `@Then` and `@And` methods inspect the stored `response` using Hamcrest matchers:

```java
@Then("the response status code should be {int}")
public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
    assertThat(response.getStatusCode(), equalTo(expectedStatusCode));
}

@And("the response should contain a list of compact discs")
public void theResponseShouldContainAListOfCompactDiscs() {
    List<?> discs = response.jsonPath().getList("$");
    assertThat("Expected a non-empty list of compact discs", discs, not(empty()));
}

@And("each compact disc should have a title and an artist")
public void eachCompactDiscShouldHaveATitleAndAnArtist() {
    List<String> titles  = response.jsonPath().getList("title");
    List<String> artists = response.jsonPath().getList("artist");
    assertThat("Every disc should have a title",  titles,  everyItem(notNullValue()));
    assertThat("Every disc should have an artist", artists, everyItem(notNullValue()));
}

@And("the response should contain a compact disc with an id of {int}")
public void theResponseShouldContainACompactDiscWithId(int expectedId) {
    int actualId = response.jsonPath().getInt("id");
    assertThat(actualId, equalTo(expectedId));
}
```

`response.jsonPath()` gives you a convenient way to navigate JSON using dot-notation paths (e.g. `"title"` for a top-level field, `"$"` for the root of the document).

---

## Part 5: Run the Tests

Make sure the `CompactDiscDaoWithRestAndBoot` application is still running, then run the tests using Maven from inside your project directory:

```
mvn test
```

You should see Cucumber print a summary similar to the following in the console:

```
4 Scenarios (4 passed)
10 Steps (10 passed)
```

An HTML report will also be written to `target/cucumber-reports/report.html` — open this in a browser for a nicely formatted view of the results.

If any scenarios fail, Cucumber will print the failing step along with the assertion error, making it easy to pinpoint what went wrong.

---

## Extension Exercises

Once all four scenarios are passing, try the following:

1. **Add a scenario that expects a 404** – call `GET /api/compactdiscs/404/{id}` with an ID that does not exist and verify that the status code is 404. You will need to add a new step definition to handle the assertion.

2. **Use a Scenario Outline** – refactor the "Retrieve a compact disc by ID" scenario into a `Scenario Outline` with an `Examples` table containing two or three different valid IDs.

3. **Add tagging** – tag your read scenarios with `@read` and your write scenarios with `@write`. Then run only the read scenarios using:
   ```
   mvn test -Dcucumber.filter.tags="@read"
   ```
