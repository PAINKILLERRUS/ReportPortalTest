package POM.selenium;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$$;

public class LaunchesPage extends BaseSeleniumPage {

    public LaunchesPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class, 'sidebar__sidebar-btn--DE02C')]")
    private List<WebElement> dashboardButton;

    public DashboardPage clickDashboardBtn() throws InterruptedException {
        $$(dashboardButton).get(0).shouldBe(enabled).click();
        return new DashboardPage();
    }
}
