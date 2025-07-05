package POM.selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static service.SelenideService.findByText;

@Getter
public class DashboardPage {

    private final SelenideElement dashboardsButton = $(byXpath("//*[@id=\"app\"]/div/div/div/div/div[1]/aside/div[2]/div[1]/div/div/a"));
    private final SelenideElement addNewDashboardButton = $(findByText("Add New Dashboard"));
    private final SelenideElement addButton = $(byText("Add"));
    private final SelenideElement allDashboardsButton = $(byXpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div[1]/ul/li[1]/a"));
    private final SelenideElement deleteIcon = $(byAttribute("class", "icon__icon--coE7b icon__icon-delete--lwBwP"));
    private final SelenideElement deleteButton = $(byAttribute("class", "bigButton__big-button--BmG4Q bigButton__color-tomato--jXOiC"));
    private final SelenideElement addNewWidget = $(findByText("Add new widget"));

    private final SelenideElement enterDashboardNameField = $(byAttribute("placeholder", "Enter dashboard name"));
    private final SelenideElement enterDashboardDescriptionField = $(byAttribute("placeholder", "Enter dashboard description"));
    private final SelenideElement searchByNameField = $(byAttribute("placeholder", "Search by name"));

    public SelenideElement getSearchResultButton(String searchQuery) {
        return $(byValue(searchQuery));
    }
}
