@Logout @Regression
Feature: Logout

  Background:
    Given Already successsfully logged in as "bendahara"
    And User on the "Dashboard" page

  @Successful
  Scenario: User logs out successsfully
    When User clicks on "Logout" button
    Then Confirmation popup should be displayed
    When User clicks on "Yes" button
    Then User should be redirected to the "Login" page
