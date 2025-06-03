package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class, 'ghostButton__text--SjHtK')]//a[text()='Add new widget']")
    private WebElement addNewWidget;

    @FindBy(xpath = "//div[@class='widgetTypeItem__widget-type-item-name--WYizn widgetTypeItem__active--MZXpV']//a[text()='Overall statistics')]")
    private WebElement overallStatistics;

    @FindBy(xpath = "//*[contains(@class, 'ghostButton__text--SjHtK')]")
    private WebElement nextStep;

    @FindBy(xpath = "//*[contains(@class, 'filterName__name--B4z4P')]")
    private WebElement demoFilter;

    @FindBy(xpath = "//*[contains(@placeholder, 'Enter widget name')]")
    private WebElement addWidgetName;

    @FindBy(xpath = "//*[contains(@class, 'bigButton__big-button--BmG4Q bigButton__color-booger--EpRlL')]")
    private WebElement addWidget;

    @FindBy(xpath = "//*[contains(@class, 'filterName__name--B4z4P')]")
    private WebElement widgetType;

    public void setAddNewWidget() {
        addNewWidget.click();
    }

    public void inputOverallStatistics(){
        overallStatistics.click();
    }

    public void inputNexStep(){
        nextStep.click();
    }

    public void inputDemoFilter(){
        demoFilter.click();
    }

    public void inputAdd(){
        addNewWidget.click();
    }

    public void inputWidgetName(String name) {
        addWidgetName.clear();
        addWidgetName.sendKeys(name);
    }
}
