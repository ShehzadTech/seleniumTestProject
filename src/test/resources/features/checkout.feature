@functionality
Feature: SauceDemo checkout
  Scenario: Complete checkout with valid data
    Given I have "Sauce Labs Backpack" in my cart
    When I proceed to checkout
    And I fill in the checkout form with:
      | firstName | lastName | postalCode |
      | Shehzad-ul  | Hassan | 12345   |
    And I finish the checkout process
    Then I should see the confirmation message "THANK YOU FOR YOUR ORDER"