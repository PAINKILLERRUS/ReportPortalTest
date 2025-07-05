package POM.selenium;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static enums.TestObjectName.DASHBOARD;
import static service.NameService.getUniqueDashboardName;

public class DashboardPage extends BaseSeleniumPage {

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()='Add New Dashboard']")
    private WebElement addNewDashboard;

    @FindBy(xpath = "//*[contains(@placeholder, 'Enter dashboard name')]")
    private WebElement dashboardName;

    @FindBy(xpath = "//*[contains(@placeholder, 'Enter dashboard description')]")
    private WebElement descriptionName;

    @FindBy(xpath = "//*[text()='Add']")
    private WebElement addDashboard;

    public DashboardPage createNewDashboard() throws InterruptedException {
        $(addNewDashboard).shouldBe(visible, enabled).click();
        $(dashboardName).sendKeys(getUniqueDashboardName(DASHBOARD.getPublicName()));
        $(descriptionName).sendKeys("TestDashboard");
        addDashboard.click();
        return this;
    }
}
