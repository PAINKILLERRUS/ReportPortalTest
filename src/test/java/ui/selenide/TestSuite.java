package ui.selenide;

import POM.selenide.LoginPage;
import com.codeborne.selenide.Configuration;
import configuration.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

/**
 * Базовый класс для инициализации Selenide
 */
public abstract sealed class TestSuite permits DashboardUiTest, ApiKeyUiTest {

    private final ConfigReader configReader = new ConfigReader();

    @Setter
    private static WebDriver driver;

    /**
     * Инициализация Selenide с настройками
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeSuite(alwaysRun = true)
   // @Parameters({"browser", "baseUrl"})
    public void init() {
//        Configuration.fileDownload = FileDownloadMode.FOLDER;
//        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
//        Configuration.headless = false;
//        Configuration.reopenBrowserOnFail = true;
//        System.setProperty("webdriver.chrome.driver", configReader.getProperty("chromedriver"));
//        System.setProperty("selenide.browser", "Chrome");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        TestSuite.setDriver(driver);

        if (isRemote()) {
            Configuration.remote = "";
        }
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    public void tearDown() {
        driver.close();
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
