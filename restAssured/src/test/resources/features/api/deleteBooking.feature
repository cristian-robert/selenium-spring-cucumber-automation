@APITest
@DeleteTest
Feature: Delete operation on the Restful Booker API

  Scenario: Delete a booking
    Given I make a GET request to retrieve a random booking
    When I make a DELETE request to remove the previous booking
    Then I should receive a 201 status code
    And the booking should no longer exist