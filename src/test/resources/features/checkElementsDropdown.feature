Feature: Check every elements dropdown element

  Scenario Outline: Access all dropdown elements
    Given I am on "Elements" page
    When I see "Elements" dropdown is not collapsed
    And I click on "<element>" option
    Then I should be on the "<page>" page

    Examples:
      | element               | page               |
      | Text Box              | text-box           |
      | Check Box             | checkbox           |
      | Radio Button          | radio-button       |
      | Web Tables            | webtables          |
      | Buttons               | buttons            |
      | Links                 | links              |
      | Broken Links - Images | broken             |
      | Upload and Download   | upload-download    |
      | Dynamic Properties    | dynamic-properties |