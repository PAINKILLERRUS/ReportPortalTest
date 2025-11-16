package ui.selenide;

import POM.selenide.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import configuration.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

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
    public void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*",
                "--disable-gpu",
                "--headless=new",
                "--no-first-run",
                "--disable-extensions",
                "--disable-background-networking",
                "--disable-background-timer-throttling",
                "--disable-backgrounding-occluded-windows",
                "--disable-breakpad",
                "--disable-client-side-phishing-detection",
                "--disable-component-extensions-with-background-pages",
                "--disable-default-apps",
                "--disable-features=TranslateUI",
                "--disable-hang-monitor",
                "--disable-ipc-flooding-protection",
                "--disable-popup-blocking",
                "--disable-prompt-on-repost",
                "--disable-renderer-backgrounding",
                "--disable-sync"
        );

        // Отключаем user-data-dir полностью
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.reopenBrowserOnFail = true;
        authorization();
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

    public String getBaseUrl() {
        return configReader.getProperty("base.url").concat("/ui/#login");
    }
}
