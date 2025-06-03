package api;

import dto.ServerResponse;
import dto.api_key.KeyDTO;
import dto.dashboard.DashboardIdDTO;
import dto.find_all_dashboards.Content;
import dto.find_all_dashboards.Dashboard;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.api.ApiSteps;

import java.util.List;

import static api.DashboardUtils.*;
import static enums.ServerMessage.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API")
@Feature("Dashboard")
public class DashboardApiTest {

    private final ApiSteps step = new ApiSteps();

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Получение ключа")
    public void testGetKey() {
        KeyDTO key = createAPIKey();

        Allure.addAttachment("Key name: ", key.getName());
        Allure.addAttachment("Key id: ", String.valueOf(key.getId()));
        assertNotNull(key.getName(), "Проверка на наличие имени у созданного ключа");
        assertNotNull(key.getId(),"Проверка на наличие Id у созданного ключа");
    }

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Удаление ключа")
    public void testDeleteKey() {
        KeyDTO key = createAPIKey();
        ServerResponse deleteResponse = step.deleteApiKey(key.getId());

        String messageAboutDeletion = DELETE_API_KEY_RESPONSE.getMessage().replace("{id}", String.valueOf(key.getId()));

        assertEquals(messageAboutDeletion, deleteResponse.getMessage(), "Соответствие информативного сообщения об удалении API KEY");
    }

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Создание Dashboard")
    public void testCreateDashboard() {
        DashboardIdDTO dashboard = createDashboard();
        List<Dashboard> dashboardsList = step.findAllDashboards();
        List<List<Content>> contentList = dashboardsList.stream().map(Dashboard::getContent).toList();
        List<Integer> dashboardsId = contentList.stream().flatMap(List::stream).toList().stream().map(Content::getId).toList();

        boolean existsDashboardId = dashboardsId.contains(dashboard.getId());

        Allure.addAttachment("Dashboard id: ", String.valueOf(dashboard.getId()));
        assertNotNull(dashboard.getId(), "Проверка на наличие Id у созданного Dashboard");
        assertTrue(existsDashboardId, "Проверка на наличие Id созданного Dashboard в общем списке");
    }

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Создание и удаление Dashboard")
    public void testCreateAndDeleteDashboard() {
        DashboardIdDTO dashboard = createDashboard();
        ServerResponse deleteResponse = step.delete(dashboard.getId());

        String messageAboutDeletion = DELETE_DASHBOARD_RESPONSE.getMessage().replace("{id}", String.valueOf(dashboard.getId()));

        Allure.addAttachment("Dashboard id: ", String.valueOf(dashboard.getId()));
        assertNotNull(dashboard.getId(), "Проверка на наличие Id у созданного Dashboard");
        assertEquals(messageAboutDeletion, deleteResponse.getMessage(), "Соответствие информативного сообщения об удалении Dashboard");
    }

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Создание Dashboard с пустым именем")
    public void testCreateDashboardWithEmptyNameError() {
        ServerResponse createResponse = createDashboardWithError("Empty");

        assertEquals(ERROR_MESSAGE_EMPTY_VALUE.getMessage(), createResponse.getMessage(), "Соответствие информативного сообщения об ошибке");
    }

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Создание Dashboard с именем недопустимого размера")
    public void testCreateDashboardWithNameOfInvalidSizeError() {
        ServerResponse createResponse = createDashboardWithError("Size");

        assertEquals(ERROR_MESSAGE_INVALID_SIZE.getMessage(), createResponse.getMessage(), "Соответствие информативного сообщения об ошибке");
    }

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Получение списка всех Dashboard")
    public void testFindAllDashboards() {
        List<Dashboard> dashboardsList = step.findAllDashboards();
        List<List<Content>> contentList = dashboardsList.stream().map(Dashboard::getContent).toList();

        assertNotNull(contentList, "Проверка на наличие сущностей в списке");
    }

    @Test
    @Owner("Антипов Иван")
    @DisplayName("Поиск Dashboard по Id")
    public void testFindDashboardById() {
        DashboardIdDTO dashboard = createDashboard();
        Content findDashboard = step.getById(dashboard.getId());

        Allure.addAttachment("Dashboard id: ", String.valueOf(dashboard.getId()));
        assertEquals(dashboard.getId(), findDashboard.getId(), "Соответствие Id");
    }
}
