package steps.ui;

import POM.selenide.ApiKeyPage;
import com.codeborne.selenide.Selenide;
import dto.HubDTO;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static org.testng.AssertJUnit.assertEquals;

public non-sealed class ApiKeyUiSteps extends UISteps {

    private final ApiKeyPage apiKeyPage = new ApiKeyPage();

    @Step("Создание Api ключа")
    public ApiKeyUiSteps generateApiKey(HubDTO item) {
        click(apiKeyPage.getUserProfileButton());
        apiKeyPage.getProfileButton().shouldBe(visible, Duration.ofSeconds(5)).scrollTo().click();
        click(apiKeyPage.getApiKeysButton());
        click(apiKeyPage.getGenerateApiKeysButton().shouldBe(visible, enabled));
        setValue(apiKeyPage.getApiKeyNameField(), item.getKey().getName());
        click(apiKeyPage.getGenerating());
        String generatingKeyTittle = apiKeyPage.getGeneratingApiKeyWindowTittle().getText();
        click(apiKeyPage.getCloseButton());
        assertEquals("GENERATE API KEY", generatingKeyTittle);
        checkConditions(item.getKey().getName(), visible);
        return this;
    }

    @Step("Удаление Api ключа")
    public ApiKeyUiSteps deletingApiKey(HubDTO item) {
        generateApiKey(item);
        Selenide.refresh();
        click(apiKeyPage.getRevokeTheKeyButton());
        click(apiKeyPage.getRevokeButton());
        apiKeyPage.getInfoMessageAboutRevokingApiKey().shouldBe(visible, Duration.ofSeconds(5));
        checkConditions(item.getKey().getName(), not(visible));
        return this;
    }

}
