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
    @BeforeMethod()
    public void init() {
        WebDriverManager.chromedriver().setup();
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.reopenBrowserOnFail = true;
        authorization();

//        if (isRemote()) {
//            Configuration.remote = "";
//        }
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterMethod
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

//    private boolean isRemote() {
//        return java.util.Optional
//                .ofNullable(System.getProperty("os.name"))
//                .filter(s -> !s.contains("Windows"))
//                .isPresent();
//    }

    public String getBaseUrl() {
        return configReader.getProperty("base.url").concat("/ui/#login");
    }
}
