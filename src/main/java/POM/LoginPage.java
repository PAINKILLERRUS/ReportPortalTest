package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@name, 'login')]")
    private WebElement loginField;

    @FindBy(xpath = "//*[contains(@name, 'password')]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[contains(@class, 'bigButton__big-button--BmG4Q bigButton__rounded-corners--c_Xiz bigButton__color-organish--gj0Mz')]")
    private WebElement loginButton;

    /**
     * Метод ввода логина не употребляется в тесте по причине автоматического ввода при переходе на страницу
     *
     * @param login
     */
    public void inputLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
    }

    /**
     * Метод ввода пароля не употребляется в тесте по причине автоматического ввода при переходе на страницу
     *
     * @param password
     */
    public void inputPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
