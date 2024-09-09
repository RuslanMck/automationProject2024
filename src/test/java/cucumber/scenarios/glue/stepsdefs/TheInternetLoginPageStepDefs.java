package cucumber.scenarios.glue.stepsdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TheInternetLoginPageStepDefs {

    @Given("user is opened the Internet login page")
    public void userOpensLoginPage() {
        System.out.println("123");
    }

    @When("^user enters '(.*)' in login firld and '(.*)' in password field$")
    public void userEntersCredentials(String login, String password) {
        System.out.println(login + " " + password);
    }

    @And("clicks 'login' button")
    public void userClicksLoginButton() {
        System.out.println("clicks 'login' button");
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        System.out.println("user is logged in");
    }

    @Then("user sees error message")
    public void errorMessageDisplaying() {
        System.out.println("user sees error message");
    }

}
