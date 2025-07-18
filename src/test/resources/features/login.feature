 @login @positive
Feature: SauceDemo Login

  Scenario: Login with valid credentials
    Given I am on the SauceDemo login page
    When I login with username and password
    Then I should be redirected to the inventory page

  Scenario: Login with locked out user
    When I login with locked user credentials
    Then  I should see an error message

  Scenario: Login with random invalid credentials
    Given I am on the SauceDemo login page
    When I login with random credentials
    Then I should see an error message "Username and password do not match any user in this service"

  Scenario: Login with incorrect password
    When I login with username and wrong password
    Then I should see an error message "Username and password do not match any user in this service"
  Scenario: Login using config
    Given I am on the SauceDemo login page
    When I login with credentials from config
    Then I should be redirected to the inventory page
