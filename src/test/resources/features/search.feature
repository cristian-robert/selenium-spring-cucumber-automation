#Feature: Google search
#
#  @smoke
#  Scenario Outline: 01 - Search for "cucumber"
#    Given I am on the Google search page
#    When I enter "<keyword>" as a keyword
#    And I click on the search button
#    Then I should see at least <count> results
#
#    Examples:
#      | keyword  | count |
#      | spring   | 5     |
#      | selenium | 2     |
#      | java     | 5     |