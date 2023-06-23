Feature: Text box tests

  Scenario Outline: Check text box positive scenario
    Given I am on "text-box" page
    When I fill full name as "<fullname>"
    And I fill email as "<email>"
    And I fill current address as "<currentaddress>"
    And I fill permanent address as "<permanentaddress>"
    And I click on submit button
    Then I validate the "<fullname>" "<email>" "<currentaddress>" "<permanentaddress>"

    Examples:
      | fullname   | email                        | currentaddress             | permanentaddress             |
      | Automation | demoqa-automation@google.com | This is my current address | This is my permanent address |

  Scenario Outline: Check text box negative scenario
    Given I am on "text-box" page
    When I fill full name as "<fullname>"
    And I fill email as "<email>"
    And I fill current address as "<currentaddress>"
    And I fill permanent address as "<permanentaddress>"
    And I click on submit button
    Then I check for field errors

    Examples:
      | fullname   | email           | currentaddress             | permanentaddress             |
      | Automation | demoqa-automati | This is my current address | This is my permanent address |


