Feature: Kafka

  Scenario: Kafka
    Given I send a message for calculation with "1" and "2" to add
    Then I should receive the message "3" from kafka result topic