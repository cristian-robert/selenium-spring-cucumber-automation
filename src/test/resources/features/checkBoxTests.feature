Feature: Check box tests

  Scenario Outline: Checkbox test scenario
    Given I am on "checkbox" page
    When I click on the "<dropdownFirst>" dropdown
    And I click on the "<dropdownSecond>" dropdown
    And I click on the "<checkboxFirst>" checkbox
    And I click on the "<checkboxSecond>" checkbox
    Then I see I have selected "<checkboxSecond>" "<checkboxFirst>" checkboxes

    Examples:
      | dropdownFirst | dropdownSecond | checkboxFirst | checkboxSecond |
      | home          | desktop        | notes         | commands       |
      | home          | downloads      | wordFile      | excelFile      |

