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
import org.testng.annotations.Parameters;

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
    @Parameters({"browser", "baseUrl"})
    public void init() {
        WebDriverManager.chromedriver().setup();

        // Настройки Selenide
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.savePageSource = false;

        // Chrome options для Jenkins
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*",
                "--disable-gpu",
                "--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis()
        );

        // Отключаем автоматическое управление user-data-dir в Selenide
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("java.io.tmpdir"));
        options.setExperimentalOption("prefs", prefs);

        Configuration.browserCapabilities = options;
//        WebDriverManager.chromedriver().setup();
//        Configuration.fileDownload = FileDownloadMode.FOLDER;
//        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "Chrome";
//        Configuration.headless = true;
//        Configuration.timeout = 10000;
//        //Configuration.reopenBrowserOnFail = true;
//        //System.setProperty("webdriver.chrome.driver", configReader.getProperty("chromedriver"));
        authorization();

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
