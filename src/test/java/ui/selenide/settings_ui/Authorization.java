package ui.selenide.settings_ui;

import POM.selenide.LoginPage;
import configuration.ConfigReader;

import static com.codeborne.selenide.Selenide.open;

public class Authorization {

    private void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.getLoginField().setValue(new ConfigReader().getProperty("login"));
        loginPage.getPasswordField().setValue(new ConfigReader().getProperty("password"));
        loginPage.getLogin().click();
    }

    public String getBaseUrl() {
        return new ConfigReader().getProperty("base.url").concat("/ui/#login");
    }

    public void authorization() {
        open(getBaseUrl());
        login();
    }
}
