package POM.selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class LoginPage {

    private final SelenideElement loginField = $(byAttribute("placeholder", "Login"));
    private final SelenideElement passwordField = $(byAttribute("placeholder", "Password"));
    private final SelenideElement login = $(byText("Login"));

}
