package test.java.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement loginField = $(byId("login-email"));
    public SelenideElement passwordField = $(byId("login-password"));
    public SelenideElement signInButton = $(byXpath("//button[@type='submit']"));
    public SelenideElement errorMessage = $(byXpath("//div[contains(@class,'alert-danger')]/p"));
    public SelenideElement loginPasswordError = $(byId("login-password-error"));
    public SelenideElement loginEmailError = $(byId("login-email-error"));

    public void doLogin(String login, String password) {
        loginField.val(login);
        passwordField.val(password);
        signInButton.click();
    }
}
