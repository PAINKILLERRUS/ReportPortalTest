package ui.service;

import configuration.ConfigReader;
import core.BaseSeleniumPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseSeleniumTest {
    private static WebDriver driver;
    private static final ConfigReader CONFIG_READER = new ConfigReader();


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", CONFIG_READER.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().getPageLoadTimeout();
        BaseSeleniumPage.setDriver(driver);
        driver.get(CONFIG_READER.getProperty("base.url").concat("/ui/#login"));
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}
