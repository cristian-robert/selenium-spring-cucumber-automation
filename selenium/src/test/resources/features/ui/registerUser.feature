Feature: Registering a new user

    @UITest
    Scenario: User register success
        Given I am on the register page
        When I fill in the register form with random data
        And I press Register
        Then I should see logout button from my account page
