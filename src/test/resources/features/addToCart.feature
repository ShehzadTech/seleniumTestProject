@functionality
Feature: Adding products to cart

  Scenario: Add a single product to the cart
    Given User is logged in
    When User adds the product to the cart
    Then the cart badge should display "1"
    And the cart should contain "Sauce Labs Backpack"