package com.prasaddys.stepdefinitions.signin;

import com.prasaddys.pageobjects.signin.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignInSteps {

    private final Logger log = LoggerFactory.getLogger(SignInSteps.class);
    private final SignInPage signInPage = new SignInPage();

    @Given("User navigates to crm signin page")
    public void userNavigatesToCrmSignInPage() {
    }

    @When("User signin with valid username {string} and password {string}")
    public void userSignInWithValidUsernameAndPassword(String email, String password) {
        signInPage.signIn(email, password);
    }

    @When("User signin with invalid username {string} and password {string}")
    public void userSignInWithInvalidUsernameAndPassword(String email, String password) {
        signInPage.signIn(email, password);
    }

    @And("Click SignIn button")
    public void clickSignInButton() {
        signInPage.clickSignInButton();
    }

    @Then("User is redirected to the Dashboard page")
    public void userIsRedirectedToTheDashboardPage() {
    }

    @And("The error message is displays")
    public void theErrorMessageIsDisplays() {
    }

}
