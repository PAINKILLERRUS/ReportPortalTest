package steps.api;

import dto.ServerResponse;
import dto.dashboard.DashboardIdDTO;
import dto.find_all_dashboards.Content;
import dto.widget.AddWidgetDTO;
import dto.widget.WidgetInfo;
import io.qameta.allure.Step;
import service.DashboardService;

import java.util.List;

public class DashboardSteps {

    private final DashboardService service = new DashboardService();

    @Step("Создание Dashboard с ошибкой")
    public <T> ServerResponse createDashboardWithError(T data) {
        return service.createDashboardWithError(data);
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
    public List<Content> findAllDashboards() {
        return service.getAll();
    }

    @Step("Создание Widget")
    public <T> DashboardIdDTO createWidget(T data) {
        return service.createWidget(data);
    }

    @Step("Добавление Widget к Dashboard")
    public ServerResponse addWidgetToDashboard(final String id, AddWidgetDTO data) {
        return service.addWidgetToDashboard(id, data);
    }

    @Step("Получение информации о Widget")
    public WidgetInfo getWidgetInformation(final String widgetId) {
        return service.getWidgetInformation(widgetId);
    }
}
