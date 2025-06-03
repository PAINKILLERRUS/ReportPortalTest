package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllDashboardPage {

    public AllDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class, 'gridCell__grid-cell--EIqeC gridCell__align-left--DFXWN dashboardTable__name--t2a89') and contains(text(), 'DEMO DASHBOARD')]")
    private WebElement demoDashboard;

    public void clickDashboard() {
        demoDashboard.click();
    }
}
