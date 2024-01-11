@APITest
Feature: Create booking on the Restful Booker API

  Scenario: Create a new booking
    Given I have a new booking details
    When I make a POST request to create a booking receiving a 200 status code
    Then the booking should be created with the correct details
