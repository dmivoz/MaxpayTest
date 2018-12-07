package test.java.ui;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class LoginTest {

    public static final String LOGIN = "qa+test+user@maxpay.com";
    public static final String PASSWORD = "21TSeWbq";
    private HeaderComponent headerComponent;
    private LoginPage loginPage;

    @BeforeClass
    public void setup(){
        Configuration.browser="firefox";
        Configuration.baseUrl="https://my-sandbox.maxpay.com/";
        headerComponent = new HeaderComponent();
        loginPage = new LoginPage();
    }

    @Test
    public void successfulLogin( ) {
        open("#/signin");
        loginPage.doLogin(LOGIN, PASSWORD);
        headerComponent.headerNavbar.waitUntil(visible,10000);
        assertEquals(title(),"Dashboard — Merchant portal");
    }

    @Test
    public void invalidLogin( ) {
        open("#/signin");
        loginPage.doLogin("incorrect@login.com", "incorrect_password");
        loginPage.errorMessage.shouldHave(text("Password or email are incorrect"));
        assertEquals(title(),"Login page — Merchant portal");
    }

    @Test(dataProvider="incorrectMails")
    public void loginFieldValidation(String mail ) {
        open("#/signin");
        loginPage.loginField.val(mail);
        loginPage.passwordField.click();
        loginPage.loginEmailError.shouldHave(text("Please enter a valid email address."));
    }

    @Test
    public void passwordFieldValidation( ) {
        open("#/signin");
        loginPage.passwordField.clear();
        loginPage.loginPasswordError.shouldHave(text("Please provide a password"));
    }

    @DataProvider
    public Object[][] incorrectMails() {
        return new Object[][]{{"mail@"}, {"mail@mail"},{"@mail"},{"mail"}};
    }
}
