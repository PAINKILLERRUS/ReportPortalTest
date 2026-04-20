package ui.selenide.settings_ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Базовый класс для инициализации Selenide
 */
public class SelenideConfigurator {

    /**
     * Инициализация Selenide с настройками
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeSuite(alwaysRun = true)
    public void init() {
        WebDriverManager.chromedriver().setup();
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.reopenBrowserOnFail = true;
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterSuite
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
