package io.cucumber.a4.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Simple step definitions for Login feature - without complex dependencies
 */
public class LoginStepDefinition {

    @Given("User has opened the browser")
    public void user_has_opened_the_browser() {
        System.out.println("Browser step - mocked");
    }

    @Given("User has navigated on the login page in {string}")
    public void user_has_navigated_on_the_login_page_in(String url) {
        System.out.println("Navigate step - mocked for URL: " + url);
    }

    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        System.out.println("Valid credentials step - mocked");
    }

    @When("User enters unregistered username, but right password")
    public void user_enters_unregistered_username_but_right_password() {
        System.out.println("Unregistered username step - mocked");
    }

    @When("User enters registered username, but wrong password")
    public void user_enters_registered_username_but_wrong_password() {
        System.out.println("Wrong password step - mocked");
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        System.out.println("Login button click step - mocked");
    }

    @Then("User should be redirected to the dashboard page")
    public void user_should_be_redirected_to_the_dashboard_page() {
        System.out.println("Dashboard redirect step - mocked");
    }

    @Then("User should see the dashboard page with bendahara role")
    public void user_should_see_the_dashboard_page_with_bendahara_role() {
        System.out.println("Bendahara role step - mocked");
    }

    @Then("User should see an error message {string}")
    public void user_should_see_an_error_message(String expectedErrorMessage) {
        System.out.println("Error message step - mocked: " + expectedErrorMessage);
    }

    @Then("User should remain on the login page")
    public void user_should_remain_on_the_login_page() {
        System.out.println("Remain on login page step - mocked");
    }
}
