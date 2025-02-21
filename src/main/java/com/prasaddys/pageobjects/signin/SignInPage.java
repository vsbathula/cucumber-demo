package com.prasaddys.pageobjects.signin;

import com.codeborne.selenide.SelenideElement;
import com.prasaddys.pageobjects.basepage.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage extends BasePage {

    private final Logger log = LogManager.getLogger(SignInPage.class);

    private final SelenideElement emailInput = $("input#email");
    private final SelenideElement passwordInput = $("input#password");
    private final SelenideElement signInButton = $(byText("Login"));

    public void signIn(String username, String password) {
        type(emailInput, username);
        type(passwordInput, password);
    }

    public void clickSignInButton() {
        click(signInButton);
    }

}
