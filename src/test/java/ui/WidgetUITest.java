package ui;

import POM.AllDashboardPage;
import POM.DashboardPage;
import POM.LaunchesPage;
import POM.LoginPage;
import configuration.ConfigReader;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static POM.LaunchesPage.BANNER_TEXT;
import static enums.TestObjectName.WIDGET_NAME;
import static service.NameService.getWidgetName;

/*
Создание нового Widget
➢ Войти в систему Report Portal (логин: default; пароль: 1q2w3e).
➢ Перейти на существующий Dashboard.
➢ Добавить новый Widget типа "Task Progress".
➢ Проверить, что Widget успешно добавлен и отображается на Dashboard
Реализация тестов на нескольких браузерах
 */

@Epic("UI")
@Feature("Widget")
public class WidgetUITest {
    private static WebDriver driver;
    private static ConfigReader configReader = new ConfigReader();
    private static LoginPage loginPage;
    private static LaunchesPage launchesPage;
    private static AllDashboardPage allDashboardPage;
    private static DashboardPage dashboardPage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", configReader.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        launchesPage = new LaunchesPage(driver);
        allDashboardPage = new AllDashboardPage(driver);
        dashboardPage = new DashboardPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.get(configReader.getProperty("base.url").concat("/ui/#login"));
    }

    @Test
    @DisplayName("Авторизация с последующем созданием виджета")
    @Owner("Антипов Иван")
    public void authorizationTest() throws InterruptedException {
        checkingTheReadinessOfTheElementForOperation();
        loginPage.inputLogin(configReader.getProperty("login"));
        loginPage.inputPassword(configReader.getProperty("password"));
        loginPage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String banner = launchesPage.getAuthBanner();
        launchesPage.clickDashboardBtn();
        allDashboardPage.clickDashboard();
        Thread.sleep(2000);
        dashboardPage.inputOverallStatistics();
        dashboardPage.inputNexStep();
        dashboardPage.inputDemoFilter();
        dashboardPage.inputNexStep();
        dashboardPage.inputWidgetName(getWidgetName(WIDGET_NAME.getPublicName()));
        dashboardPage.inputAdd();

        Assertions.assertEquals(BANNER_TEXT, banner, "Проверка успешного входа в систему Report Portal");
    }

    private static void checkingTheReadinessOfTheElementForOperation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[3]/div[1]/form/div[1]/div/div/div/input"))
                ).click();
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}
