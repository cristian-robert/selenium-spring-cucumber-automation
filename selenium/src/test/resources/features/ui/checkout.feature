Feature: Checkout products

  Scenario: Check out guest user
    Given I add a product to cart
    And I access cart page
    When I apply coupon code "friends100"
    Then I should see coupon code "friends100" applied successfully
    And when I proceed to checkout
    And fill checkout form with valid data
    And click on place order button
    Then I should see order confirmation page
    And I should see order number
    And I should see order in database

    Examples:
      | value | value2 | value3 | value4 | value5 | value6 | testId  |
      | 1     | 2      | 3      | 4      | 5      | 6      | tc123   |
      | 123   | 213    | 312    | 123    | 451    | 512    | tc51324 |
      | 123   | 5412   | 123    | 512    | 1243   | 123    | tc12312 |
      | 213   | 541    | 512    | 321    | 512    | 123    | tc7654  |
      | 512   | 123    | 5412   | 512    | 123    | 541    | tc1234  |

  #IN PROGRESS
#    Scenario: Check out logged in user
#      Given I am on the login page
#        And I login with "test-automation" and "Pass1234!"
#      And I check for cart to have 0 items
#        And I add a product to cart
#        And I access cart page
#        When I apply coupon code "friends100"
#        Then I should see coupon code "friends100" applied successfully
#        And when I proceed to checkout
#        And fill checkout form with valid data
#        And click on place order button
#        Then I should see order confirmation page
#        And I should see order number
#        And I should see order in database