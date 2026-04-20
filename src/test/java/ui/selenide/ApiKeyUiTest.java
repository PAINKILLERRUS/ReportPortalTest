package ui.selenide;

import data_provider.ApiKeyDataProvider;
import dto.HubDTO;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import listeners.RetryService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ui.ApiKeyUiSteps;
import ui.selenide.settings_ui.Authorization;
import ui.selenide.settings_ui.SelenideConfigurator;

@Epic("UI")
@Feature("Api Keys")
public class ApiKeyUiTest extends SelenideConfigurator {

    private final ApiKeyUiSteps steps = new ApiKeyUiSteps();

    @BeforeMethod
    public void seUp() {
        new Authorization().authorization();
    }

    @Owner("Антипов Иван")
    @Story("Создание нового Api ключа")
    @Test(testName = "Создание нового Api ключа", groups = {"UI"}, retryAnalyzer = RetryService.class, dataProviderClass = ApiKeyDataProvider.class, dataProvider = "createApiKey")
    public void createNewApiKeyTest(HubDTO item) {
        steps.generateApiKey(item);
    }

    @Owner("Антипов Иван")
    @Story("Удаление Api ключа")
    @Test(testName = "Удаление Api ключа", groups = {"UI"}, dataProviderClass = ApiKeyDataProvider.class, dataProvider = "createApiKey")
    public void deletedApiKeyTest(HubDTO item) {
        steps.deletingApiKey(item);
    }
}
