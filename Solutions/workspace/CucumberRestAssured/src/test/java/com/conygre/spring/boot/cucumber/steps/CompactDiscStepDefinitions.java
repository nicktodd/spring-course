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

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String path) {
        response = given()
                .accept("application/json")
                .when()
                .get(path);
    }

    @When("I send a DELETE request to {string}")
    public void iSendADeleteRequestTo(String path) {
        response = given()
                .when()
                .delete(path);
    }

    @When("I send a POST request to {string} with the following details:")
    public void iSendAPostRequestWithDetails(String path, io.cucumber.datatable.DataTable dataTable) {
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
}
