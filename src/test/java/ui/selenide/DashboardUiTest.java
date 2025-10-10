package ui.selenide;

import data_provider.DashboardDataProvider;
import dto.HubDTO;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import listeners.RetryService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ui.AddDashboardUISteps;

@Epic("UI")
@Feature("Dashboard")
public final class DashboardUiTest extends TestSuite {

    private final AddDashboardUISteps steps = new AddDashboardUISteps();

    @BeforeMethod
    public void setUp() {
        authorization();
    }

    @AfterMethod
    public void closingTheTestSession() {
        tearDown();
    }

    @Owner("Антипов Иван")
    @Story("Создание нового Dashboard")
    @Test(testName = "Создание нового Dashboard", dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboard")
    public void addDashboardTest(HubDTO item) {
        steps.addDashboard(item);
    }

    @Owner("Антипов Иван")
    @Story("Удаление Dashboard")
    @Test(testName = "Удаление Dashboard", dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboard")
    public void deletedDashboard(HubDTO item) {
        steps.deleteDashboard(item);
    }

    @Owner("Антипов Иван")
    @Story("Добавление Widget в Dashboard")
    @Test(testName = "Добавление Widget в Dashboard", retryAnalyzer = RetryService.class,
            dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboardAndWidget")
    public void addNewWidget(HubDTO item) {
        steps.addWidgetIntoDashboard(item);
    }

    @Owner("Антипов Иван")
    @Story("Поиск Dashboard в общем списке")
    @Test(testName = "Поиск Dashboard в общем списке", retryAnalyzer = RetryService.class,
            dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboardAndWidget")
    public void searchDashboardInTheGeneralList(HubDTO item) {

    }

    @Owner("Антипов Иван")
    @Story("Создание нового Api ключа")
    @Test(testName = "Создание нового Api ключа", retryAnalyzer = RetryService.class,
            dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboardAndWidget")
    public void createNewApiKey(HubDTO item) {

    }

    @Owner("Антипов Иван")
    @Story("Удаление Api ключа")
    @Test(testName = "Удаление Api ключа", retryAnalyzer = RetryService.class,
            dataProviderClass = DashboardDataProvider.class, dataProvider = "createDashboardAndWidget")
    public void deletedApiKey(HubDTO item) {

    }
}
