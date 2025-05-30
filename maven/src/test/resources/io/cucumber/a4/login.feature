@Login @Regression
Feature: Login

  Background:
    Given User has opened the browser
    And User has navigated on the login page in "http://ptbsp.ddns.net:6882"

  @Successful
  Scenario: Login is successsfull with valid credentials as bendahara role
    When User enters valid username and password
    And User clicks on the login button
    Then User should be redirected to the dashboard page
    And User should see the dashboard page with bendahara role

  @Negative
  Scenario: Login is failed with invalid credential but username not registered
    When User enters unregistered username, but right password
    And User clicks on the login button
    Then User should see an error message "Incorrect username or password, please try again!"
    And User should remain on the login page

  @Negative
  Scenario: Login is failed with invalid credentials but incorrect password
    When User enters registered username, but wrong password
    And User clicks on the login button
    Then User should see an error message "Incorrect username or password, please try again!"
    And User should remain on the login page
