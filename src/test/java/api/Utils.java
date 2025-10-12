package api;

import dto.ServerResponse;
import dto.api_key.KeyDTO;
import dto.api_key.KeyNameDTO;
import dto.dashboard.DashboardIdDTO;
import dto.find_all_dashboards.WidgetPosition;
import dto.find_all_dashboards.WidgetSize;
import dto.widget.AddWidgetDTO;
import dto.widget.AddWidgetItem;
import dto.widget.WidgetDTO;
import dto.widget.WidgetInfo;
import patterns.factory_method.Dashboard;
import patterns.factory_method.DashboardFactory;
import service.JsonService;
import steps.api.DashboardSteps;
import steps.api.KeySteps;

import java.io.IOException;

import static enums.JsonPath.ADD_WIDGET_JSON;
import static enums.JsonPath.CREATE_WIDGET_JSON;
import static enums.ServerMessage.SUCCESS_ADDED_WIDGET_MESSAGE;
import static enums.TestObjectName.API_KEY;
import static enums.TestObjectName.WIDGET_NAME;
import static service.NameService.getUniqueApiKeyName;
import static service.NameService.getWidgetName;

public final class Utils {

    private static final DashboardSteps STEP = new DashboardSteps();
    private static final KeySteps KEY_STEPS = new KeySteps();

    public static String messageEditor(final String dashboardId, final String widgetId) {
        return SUCCESS_ADDED_WIDGET_MESSAGE.getMessage().replace("{widgetId}", widgetId).replace("{id}", dashboardId);
    }

    public static DashboardIdDTO createDashboard() {
        Dashboard dashboard = DashboardFactory.createDashboard("standard");
        return STEP.create(dashboard);
    }

    public static ServerResponse createDashboardWithError(String option) {

        if (option.equals("Size")) {
            Dashboard dashboardWithErrorParameter = DashboardFactory.createDashboard("error");
            return STEP.createDashboardWithError(dashboardWithErrorParameter);

        } else if (option.equals("Empty")) {
            Dashboard dashboardWithEmptyName = DashboardFactory.createDashboard("empty");
            return STEP.createDashboardWithError(dashboardWithEmptyName);

        } else throw new RuntimeException("Empty option field.");
    }

    public static KeyDTO createAPIKey() {
        KeyNameDTO name = new KeyNameDTO()
                .setName(getUniqueApiKeyName(API_KEY.getPublicName()));

        return KEY_STEPS.createApiKey(name);
    }

    public static DashboardIdDTO createWidget() throws IOException {
        WidgetDTO widget = new JsonService().getObjectFromJson(CREATE_WIDGET_JSON.getJsonPathName(), WidgetDTO.class);
        return STEP.createWidget(widget.setDescription("TestWidget").setName(getWidgetName(WIDGET_NAME.getPublicName())));
    }

    public static ServerResponse addWidget(String dashboardId, WidgetInfo info) throws IOException {
        AddWidgetDTO widget = new JsonService().getObjectFromJson(ADD_WIDGET_JSON.getJsonPathName(), AddWidgetDTO.class);
        return STEP.addWidgetToDashboard(dashboardId, widget.setAddWidget(
                new AddWidgetItem()
                        .setWidgetType(info.getWidgetType())
                        .setWidgetId(info.getId())
                        .setWidgetName(info.getName())
                        .setWidgetPosition(
                                new WidgetPosition()
                                        .setPositionX(0)
                                        .setPositionY(0)
                        )
                        .setWidgetSize(
                                new WidgetSize()
                                        .setHeight(7)
                                        .setWidth(12))));
    }
}
