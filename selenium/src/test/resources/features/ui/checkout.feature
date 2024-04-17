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