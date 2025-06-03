package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaunchesPage {
    public static final String BANNER_TEXT = "Your are on the public Demo Account. For loading your sensitive data, please use GitHub authorization";

    public LaunchesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class, 'demoBanner__description--HQs7Q')]")
    private WebElement authBanner;

    @FindBy(xpath = "//*[contains(@class, 'sidebarButton__nav-link--gZnHQ')]")
    private WebElement dashboardPanelBtn;

    public String getAuthBanner(){
        return authBanner.getText();
    }

    public void clickDashboardBtn(){
        dashboardPanelBtn.click();
    }
}
