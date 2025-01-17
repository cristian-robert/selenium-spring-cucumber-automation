Feature: Test user login feature

    @UITest
    Scenario: User login with correct credentials
        Given I am on the login page
        When I login with "test-automation" and "Pass1234!"
        Then I should see my account page logged in as "test-automation"

    @UITest
    Scenario: User login with incorrect credentials
        Given I am on the login page
        When I login with "test-automation" and "incorrect-password"
        Then I should see an error message "Error: The password you entered for the username test-automation is incorrect. Lost your password?"