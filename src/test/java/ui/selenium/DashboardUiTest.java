package ui.selenium;

import POM.selenium.DashboardPage;
import POM.selenium.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI")
@Feature("Widget")
public class DashboardUiTest extends BaseSeleniumTest {

    @Test
    @Owner("Антипов Иван")
    @Story("Авторизация с последующем созданием Dashboard")
    @DisplayName("Авторизация с последующем созданием Dashboard")
    public void authorizationTest() throws InterruptedException {
        DashboardPage page = new LoginPage()
                .authorization()
                .clickDashboardBtn()
                .createNewDashboard();

        Assertions.assertNotNull(page);
    }
}
