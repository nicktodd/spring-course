Feature: Compact Disc REST API

  Background:
    Given the compact disc API is running at "http://localhost:8080"

  Scenario: Retrieve all compact discs
    When I send a GET request to "/api/compactdiscs"
    Then the response status code should be 200
    And the response should contain a list of compact discs
    And each compact disc should have a title and an artist

  Scenario: Retrieve a compact disc by ID
    When I send a GET request to "/api/compactdiscs/16"
    Then the response status code should be 200
    And the response should contain a compact disc with an id of 16

  Scenario: Add a new compact disc
    When I send a POST request to "/api/compactdiscs" with the following details:
      | title  | artist       | price | tracks |
      | Thriller | Michael Jackson | 9.99 | 9 |
    Then the response status code should be 200

  Scenario: Delete a compact disc by ID
    When I send a DELETE request to "/api/compactdiscs/16"
    Then the response status code should be 200
