package ui.selenide;

import data_provider.ApiKeyDataProvider;
import dto.HubDTO;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import listeners.RetryService;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ui.ApiKeyUiSteps;

@Epic("UI")
@Tag("UI")
@Feature("Api Keys")
public final class ApiKeyUiTest extends TestSuite {

    private final ApiKeyUiSteps steps = new ApiKeyUiSteps();

    @BeforeMethod
    public void setUp() {
        authorization();
    }

    @AfterMethod
    public void closingTheTestSession() {
        tearDown();
    }

    @Owner("Антипов Иван")
    @Story("Создание нового Api ключа")
    @Test(testName = "Создание нового Api ключа", retryAnalyzer = RetryService.class, dataProviderClass = ApiKeyDataProvider.class, dataProvider = "createApiKey")
    public void createNewApiKeyTest(HubDTO item) {
        steps.generateApiKey(item);
    }

    @Owner("Антипов Иван")
    @Story("Удаление Api ключа")
    @Test(testName = "Удаление Api ключа", dataProviderClass = ApiKeyDataProvider.class, dataProvider = "createApiKey")
    public void deletedApiKeyTest(HubDTO item) {
        steps.deletingApiKey(item);
    }
}
