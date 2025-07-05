package steps.ui;

import POM.selenide.DashboardPage;
import POM.selenide.WidgetPage;
import com.codeborne.selenide.SelenideElement;
import dto.HubDTO;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.assertFalse;
import static service.SelenideService.findByText;

public class AddDashboardUISteps extends UISteps {

    private final DashboardPage dashboardPage = new DashboardPage();
    private final WidgetPage widgetPage = new WidgetPage();

    @Step("Добавление Dashboard")
    public AddDashboardUISteps addDashboard(HubDTO item) {
        click(dashboardPage.getDashboardsButton());
        click(dashboardPage.getAddNewDashboardButton());
        setValue(dashboardPage.getEnterDashboardNameField().shouldBe(visible, Duration.ofSeconds(5)), item.getDashboardItemDTO().getName());
        setValue(dashboardPage.getEnterDashboardDescriptionField(), item.getDashboardItemDTO().getDescription());
        click(dashboardPage.getAddButton());
        click(dashboardPage.getAllDashboardsButton());
        setValue(dashboardPage.getSearchByNameField(), item.getDashboardItemDTO().getName());
        checkConditions(dashboardPage.getSearchResultButton(item.getDashboardItemDTO().getName()), exist, 5);
        return this;
    }

    @Step("Удаление созданного Dashboard")
    public AddDashboardUISteps deleteDashboard(HubDTO item) {
        addDashboard(item);
        SelenideElement objToDeleted = findByText(item.getDashboardItemDTO().getName());
        click(dashboardPage.getDeleteIcon().shouldBe(visible, Duration.ofSeconds(5)));
        click(dashboardPage.getDeleteButton().shouldBe(visible, Duration.ofSeconds(5)));
        setValue(dashboardPage.getSearchByNameField(), item.getDashboardItemDTO().getName());
        assertFalse(objToDeleted.isDisplayed());
        return this;
    }

    @Step("Добавление Widget в Dashboard")
    public AddDashboardUISteps addWidgetIntoDashboard(HubDTO item) {
        click(dashboardPage.getDashboardsButton());
        click(dashboardPage.getAddNewDashboardButton());
        setValue(dashboardPage.getEnterDashboardNameField().shouldBe(visible, Duration.ofSeconds(5)), item.getDashboardItemDTO().getName());
        setValue(dashboardPage.getEnterDashboardDescriptionField().shouldBe(visible, Duration.ofSeconds(5)), item.getDashboardItemDTO().getDescription());
        click(dashboardPage.getAddButton());
        click(dashboardPage.getAddNewWidget());
        click(widgetPage.getSelectWidgetTypeButton().shouldBe(visible, Duration.ofSeconds(5)));
        click(widgetPage.getNextStepButton());
        widgetPage.getWidgetName().clear();
        setValue(widgetPage.getWidgetName(), item.getWidget().getName());
        setValue(widgetPage.getWidgetDescription(), item.getWidget().getDescription());
        click(widgetPage.getAddButton());
        checkConditions(findByText(item.getWidget().getName()), visible, 5);
        return this;
    }
}
