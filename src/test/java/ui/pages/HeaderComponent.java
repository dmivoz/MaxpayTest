package test.java.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {
    public SelenideElement headerNavbar = $(byId("header-navbar"));
}

