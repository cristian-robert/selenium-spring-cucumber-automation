@APITest
Feature: Update operation on the Restful Booker API

  Scenario: Update a booking
    Given I make a GET request to retrieve a random booking
    When I make a PUT request to update the previous booking
    Then the booking should be updated with the new details