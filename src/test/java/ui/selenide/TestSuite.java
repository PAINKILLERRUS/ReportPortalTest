package ui.selenide;

import POM.selenide.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import configuration.ConfigReader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.open;

/**
 * Базовый класс для инициализации Selenide
 */
public abstract sealed class TestSuite permits DashboardUiTest, ApiKeyUiTest {

    private final ConfigReader configReader = new ConfigReader();

    /**
     * Инициализация Selenide с настройками
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser", "baseUrl"})
    public void init() {
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "Chrome";
        Configuration.headless = true;
        Configuration.reopenBrowserOnFail = true;
        System.setProperty("webdriver.chrome.driver", configReader.getProperty("chromedriver"));
        //System.setProperty("selenide.browser", "Chrome");

        if (isRemote()) {
            Configuration.remote = "";
        }
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterSuite
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    public void authorization() {
        open(getBaseUrl());
        login();
    }

    private void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.getLoginField().setValue(configReader.getProperty("login"));
        loginPage.getPasswordField().setValue(configReader.getProperty("password"));
        loginPage.getLogin().click();
    }

    private boolean isRemote() {
        return java.util.Optional
                .ofNullable(System.getProperty("os.name"))
                .filter(s -> !s.contains("Windows"))
                .isPresent();
    }

    public String getBaseUrl() {
        return configReader.getProperty("base.url").concat("/ui/#login");
    }
}
