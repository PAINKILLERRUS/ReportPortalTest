package POM.selenium;

import configuration.ConfigReader;
import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseSeleniumPage {
    private static ConfigReader configReader = new ConfigReader();

    public LoginPage() {
        driver.get(configReader.getProperty("base.url").concat("/ui/#login"));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@placeholder, 'Login')]")
    private WebElement loginField;

    @FindBy(xpath = "//*[contains(@placeholder, 'Password')]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[text()='Login']")
    private WebElement loginButton;

    public LaunchesPage authorization() {
        loginField.clear();
        loginField.sendKeys(configReader.getProperty("login"));
        passwordField.clear();
        passwordField.sendKeys(configReader.getProperty("password"));
        loginButton.click();
        return new LaunchesPage();
    }
}
