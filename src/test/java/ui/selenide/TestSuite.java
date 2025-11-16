package ui.selenide;

import POM.selenide.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import configuration.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

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
//        Configuration.fileDownload = FileDownloadMode.FOLDER;
//        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
//        Configuration.headless = true;
//        Configuration.timeout = 10000;
//        Configuration.reopenBrowserOnFail = true;
//        authorization();

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
                "--disable-sync",
                "--metrics-recording-only",
                "--safebrowsing-disable-auto-update",
                "--password-store=basic",
                "--use-mock-keychain",
                "--hide-scrollbars",
                "--mute-audio",
                "--window-size=1920,1080",
                // Уникальный user-data-dir для каждого запуска
                "--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis()
        );

        // Отключаем автоматическое управление user-data-dir в Selenide
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("java.io.tmpdir"));
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Принудительно устанавливаем наши options
        Configuration.browserCapabilities = options;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.reopenBrowserOnFail = false; // Важно: отключаем переоткрытие

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
