Feature: Access every page of the website

  Scenario Outline: Access all pages
    Given I am on the homepage
    When I click on "<link>"
    Then I should be on the "<page>" page

    Examples:
      | link                    | page          |
      | Elements                | elements      |
      | Forms                   | forms         |
      | Widgets                 | widgets       |
      | Interactions            | interaction   |
      | Book Store Application  | books         |
      | Alerts, Frame & Windows | alertsWindows |
