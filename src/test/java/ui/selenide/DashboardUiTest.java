package ui.selenide;

import data_provider.DashboardDataProvider;
import dto.HubDTO;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import listeners.RetryService;
import org.testng.annotations.Test;
import steps.ui.DashboardUISteps;

@Epic("UI")
@Feature("Dashboard")
public final class DashboardUiTest extends TestSuite {

    private final DashboardUISteps steps = new DashboardUISteps();

    @Owner("Антипов Иван")
    @Story("Создание нового Dashboard")
    @Test(testName = "Создание нового Dashboard", groups = {"UI"}, dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboard")
    public void addDashboardTest(HubDTO item) {
        steps.addDashboard(item);
    }

    @Owner("Антипов Иван")
    @Story("Удаление Dashboard")
    @Test(testName = "Удаление Dashboard", groups = {"UI"}, dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboard")
    public void deletedDashboardTest(HubDTO item) {
        steps.deleteDashboard(item);
    }

    @Owner("Антипов Иван")
    @Story("Добавление Widget в Dashboard")
    @Test(testName = "Добавление Widget в Dashboard", groups = {"UI"}, retryAnalyzer = RetryService.class,
            dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboardAndWidget")
    public void addNewWidgetTest(HubDTO item) {
        steps.addWidgetIntoDashboard(item);
    }
}
