package POM.selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static service.SelenideService.findByText;

@Getter
public class DashboardPage {

    private final SelenideElement dashboardsButton = $("a[href='#default_personal/dashboard']");
    private final SelenideElement addNewDashboardButton = $(findByText("Add New Dashboard"));
    private final SelenideElement addButton = $(byText("Add"));
    private final SelenideElement deleteIcon = $(byAttribute("class", "icon__icon--coE7b icon__icon-delete--lwBwP"));
    private final SelenideElement deleteButton = $(byAttribute("class", "bigButton__big-button--BmG4Q bigButton__color-tomato--jXOiC"));
    private final SelenideElement addNewWidget = $(findByText("Add new widget"));
    private final SelenideElement noSearchResults = $(byAttribute("class", "noResultsForFilter__no-results-for-filter-hint--YsxeD"));

    private final SelenideElement enterDashboardNameField = $(byAttribute("placeholder", "Enter dashboard name"));
    private final SelenideElement enterDashboardDescriptionField = $(byAttribute("placeholder", "Enter dashboard description"));
    private final SelenideElement searchByNameField = $(byAttribute("placeholder", "Search by name"));

    private final SelenideElement objectInfoMessage = $$("._title_14lm6_32").get(0);
    private final SelenideElement createdInfoMessage = $$("._title_14lm6_32").get(1);
    private final SelenideElement deletedInfoMessage = $$("._title_14lm6_32").get(2);

    public SelenideElement getSearchResultButton(String searchQuery) {
        return $(byValue(searchQuery));
    }

}
