package api;

import dto.ServerResponse;
import dto.dashboard.DashboardIdDTO;
import dto.find_all_dashboards.Content;
import dto.widget.WidgetInfo;
import io.qameta.allure.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.api.DashboardSteps;

import java.io.IOException;
import java.util.List;

import static api.Utils.*;
import static enums.ServerMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static service.CleaningService.deleteAllUnusedObjects;

@Epic("API")
@Feature("Dashboard")
@Execution(ExecutionMode.CONCURRENT)
public class DashboardApiTest {

    private final DashboardSteps step = new DashboardSteps();

    @AfterMethod
    public static void deleteObjects() {
        deleteAllUnusedObjects();
    }

    @Owner("Антипов Иван")
    @Story("Создание Dashboard")
    @Test(testName = "Создание Dashboard", groups = {"API"})
    public void testCreateDashboard() {
        DashboardIdDTO dashboard = createDashboard();
        List<Content> dashboardsList = step.findAllDashboards();
        List<Integer> dashboardsId = dashboardsList.stream().map(Content::getId).toList();

        boolean existsDashboardId = dashboardsId.contains(dashboard.getId());

        Allure.addAttachment("Dashboard id: ", String.valueOf(dashboard.getId()));
        assertNotNull(dashboard.getId(), "Проверка на наличие Id у созданного Dashboard");
        assertTrue(existsDashboardId, "Проверка на наличие Id созданного Dashboard в общем списке");
    }

    @Owner("Антипов Иван")
    @Story("Создание и удаление Dashboard")
    @Test(testName = "Создание и удаление Dashboard", groups = {"API"})
    public void testCreateAndDeleteDashboard() {
        DashboardIdDTO dashboard = createDashboard();
        ServerResponse deleteResponse = step.delete(dashboard.getId());

        String messageAboutDeletion = DELETE_DASHBOARD_RESPONSE.getMessage().replace("{id}", String.valueOf(dashboard.getId()));

        Allure.addAttachment("Dashboard id: ", String.valueOf(dashboard.getId()));
        assertNotNull(dashboard.getId(), "Проверка на наличие Id у созданного Dashboard");
        assertEquals(messageAboutDeletion, deleteResponse.getMessage(), "Соответствие информативного сообщения об удалении Dashboard");
    }

    @Owner("Антипов Иван")
    @Story("Создание Dashboard с пустым именем")
    @Test(testName = "Создание Dashboard с пустым именем", groups = {"API"})
    public void testCreateDashboardWithEmptyNameError() {
        ServerResponse createResponse = createDashboardWithError("Empty");

        assertEquals(ERROR_MESSAGE_EMPTY_VALUE.getMessage(), createResponse.getMessage(), "Соответствие информативного сообщения об ошибке");
    }

    @Owner("Антипов Иван")
    @Story("Создание Dashboard с именем недопустимого размера")
    @Test(testName = "Создание Dashboard с именем недопустимого размера", groups = {"API"})
    public void testCreateDashboardWithNameOfInvalidSizeError() {
        ServerResponse createResponse = createDashboardWithError("Size");

        assertEquals(ERROR_MESSAGE_INVALID_SIZE.getMessage(), createResponse.getMessage(), "Соответствие информативного сообщения об ошибке");
    }

    @Owner("Антипов Иван")
    @Story("Получение списка всех Dashboard")
    @Test(testName = "Получение списка всех Dashboard", groups = {"API"})
    public void testFindAllDashboards() {
        List<Content> dashboardsList = step.findAllDashboards();

        assertNotNull(dashboardsList, "Проверка на наличие сущностей в списке");
    }

    @Owner("Антипов Иван")
    @Story("Поиск Dashboard по Id")
    @Test(testName = "Поиск Dashboard по Id", groups = {"API"})
    public void testFindDashboardById() {
        DashboardIdDTO dashboard = createDashboard();
        Content findDashboard = step.getById(dashboard.getId());

        Allure.addAttachment("Dashboard id: ", String.valueOf(dashboard.getId()));
        assertEquals(dashboard.getId(), findDashboard.getId(), "Соответствие Id");
    }

    @Owner("Антипов Иван")
    @Story("Добавление Widget к Dashboard")
    @Test(testName = "Добавление Widget к Dashboard", groups = {"API"})
    public void testAddWidgetToDashboard() throws IOException {
        DashboardIdDTO dashboard = createDashboard();
        DashboardIdDTO widget = createWidget();
        WidgetInfo widgetInfo = step.getWidgetInformation(widget.getId().toString());
        ServerResponse addWidgetResponse = addWidget(String.valueOf(dashboard.getId()), widgetInfo);

        Allure.addAttachment("Dashboard id: ", String.valueOf(dashboard.getId()));
        Allure.addAttachment("Widget id: ", String.valueOf(dashboard.getId()));
        assertNotNull(dashboard.getId(), "Проверка на наличие Id у созданного Dashboard");
        assertNotNull(widget.getId(), "Проверка на наличие Id у созданного Widget");
        assertEquals(addWidgetResponse.getMessage(), messageEditor(dashboard.getId().toString(), widget.getId().toString()));
    }
}
