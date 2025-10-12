package steps.ui;

import POM.selenide.DashboardPage;
import POM.selenide.WidgetPage;
import dto.HubDTO;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.assertEquals;
import static service.SelenideService.findByText;

public non-sealed class DashboardUISteps extends UISteps {

    private final DashboardPage dashboardPage = new DashboardPage();
    private final WidgetPage widgetPage = new WidgetPage();

    @Step("Добавление Dashboard")
    public DashboardUISteps addDashboard(HubDTO item) {
        click(dashboardPage.getDashboardsButton());
        click(dashboardPage.getAddNewDashboardButton());
        setValue(dashboardPage.getEnterDashboardNameField().shouldBe(visible, Duration.ofSeconds(5)), item.getDashboardItemDTO().getName());
        setValue(dashboardPage.getEnterDashboardDescriptionField(), item.getDashboardItemDTO().getDescription());
        click(dashboardPage.getAddButton());
        String infoMessage = dashboardPage.getObjectInfoMessage().getText();
        assertEquals(infoMessage, "Signed in successfully");
        String createdMessage = dashboardPage.getCreatedInfoMessage().getText();
        assertEquals(createdMessage, "Dashboard has been created successfully.");
        click(dashboardPage.getDashboardsButton());
        setValue(dashboardPage.getSearchByNameField(), item.getDashboardItemDTO().getName());
        checkConditions(dashboardPage.getSearchResultButton(item.getDashboardItemDTO().getName()), exist, 5);
        return this;
    }

    @Step("Удаление созданного Dashboard")
    public DashboardUISteps deleteDashboard(HubDTO item) {
        addDashboard(item);
        findByText(item.getDashboardItemDTO().getName());
        click(dashboardPage.getDeleteIcon().shouldBe(visible, Duration.ofSeconds(5)));
        click(dashboardPage.getDeleteButton().shouldBe(visible, Duration.ofSeconds(5)));
        String deletedMessage = dashboardPage.getDeletedInfoMessage().getText();
        assertEquals(deletedMessage, "Dashboard has been deleted");
        setValue(dashboardPage.getSearchByNameField(), item.getDashboardItemDTO().getName());
        assertEquals(dashboardPage.getNoSearchResults().getText(), "Check your query and try again");
        return this;
    }

    @Step("Добавление Widget в Dashboard")
    public DashboardUISteps addWidgetIntoDashboard(HubDTO item) {
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
