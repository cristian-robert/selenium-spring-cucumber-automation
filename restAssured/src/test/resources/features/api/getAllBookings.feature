@APITest
Feature: Get all bookings

  Scenario: Get all bookings
    Given I make a request to get all bookings
    Then I should see all bookings

  Scenario: Get Random Booking information
    Given I make a request to get all bookings
    Then I should see a random booking