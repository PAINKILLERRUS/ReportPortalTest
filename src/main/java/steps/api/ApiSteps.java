package steps.api;

import dto.ServerResponse;
import dto.api_key.KeyDTO;
import dto.api_key.KeyNameDTO;
import dto.dashboard.DashboardIdDTO;
import dto.dashboard.DashboardItemDTO;
import dto.find_all_dashboards.Content;
import dto.find_all_dashboards.Dashboard;
import io.qameta.allure.Step;
import service.ApiService;

import java.util.List;

public class ApiSteps {

    private final ApiService service = new ApiService();

    @Step("Создание и получение API Key")
    public KeyDTO createApiKey(KeyNameDTO name) {
        return service.createApiKey(name);
    }

    @Step("Удаление ключа")
    public ServerResponse deleteApiKey(int keyId) {
        return service.deleteApiKey(keyId);
    }

    @Step("Создание Dashboard с ошибкой")
    public ServerResponse createDashboardWithError(DashboardItemDTO item) {
        return service.createDashboardWithError(item);
    }

    @Step("Удаление Dashboard")
    public ServerResponse delete(int id) {
        return service.delete(id);
    }

    @Step("Поиск Dashboard по id-{id}")
    public Content getById(int id) {
        return service.getById(id);
    }

    @Step("Создание Dashboard")
    public <T> DashboardIdDTO create(T data) {
        return service.create(data);
    }

    @Step("Получение списка всех Dashboard")
    public List<Dashboard> findAllDashboards() {
        return service.findAllDashboards();
    }
}
