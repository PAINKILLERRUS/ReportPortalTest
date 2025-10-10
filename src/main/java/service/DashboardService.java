package service;

import dto.ServerResponse;
import dto.dashboard.DashboardIdDTO;
import dto.dashboard.DashboardItemDTO;
import dto.find_all_dashboards.Content;
import dto.find_all_dashboards.Dashboard;
import dto.widget.AddWidgetDTO;
import dto.widget.WidgetInfo;
import service.rest_assured.ApiInterface;
import service.rest_assured.Request;

import java.util.Collections;
import java.util.List;

public class DashboardService implements ApiInterface {

    public static final String GET_TOKEN = "/uat/sso/oauth/token";
    public static final String CREATE_DASHBOARD = "/api/v1/default_personal/dashboard";
    private static final String GET_DASHBOARD = "/api/v1/default_personal/dashboard/";
    private static final String CREATE_WIDGET = "/api/v1/default_personal/widget";
    private static final String GET_WIDGET_INFO = "/api/v1/default_personal/widget/";
    private static final String ADD_WIDGET_TO_DASHBOARD = "/api/v1/default_personal/dashboard/{id}/add";


    public ServerResponse createDashboardWithError(DashboardItemDTO item) {
        return new Request().post(CREATE_DASHBOARD, item, ServerResponse.class, 400);
    }

    @Override
    public ServerResponse delete(int id) {
        return new Request().delete(GET_DASHBOARD.concat(String.valueOf(id)), ServerResponse.class, 200);
    }

    @Override
    public Content getById(final int id) {
        return new Request().get(GET_DASHBOARD.concat(String.valueOf(id)), Content.class, 200);
    }

    @Override
    public <T> DashboardIdDTO create(T data) {
        return new Request().post(CREATE_DASHBOARD, data, DashboardIdDTO.class, 201);
    }

    @Override
    public List<Content> getAll() {
        Dashboard dashboard = new Request().get(CREATE_DASHBOARD, Dashboard.class, 200);
        return dashboard.getContent();
    }

    public List<Dashboard> findAllDashboards() {
        return Collections.singletonList(new Request().get(CREATE_DASHBOARD, Dashboard.class, 200));
    }

    public <T> DashboardIdDTO createWidget(T data) {
        return new Request().post(CREATE_WIDGET, data, DashboardIdDTO.class, 201);
    }

    public ServerResponse addWidgetToDashboard(final String id, AddWidgetDTO data) {
        return new Request().put(ADD_WIDGET_TO_DASHBOARD.replace("{id}", id), data, ServerResponse.class, 200);
    }

    public WidgetInfo getWidgetInformation(final String widgetId) {
        return new Request().get(GET_WIDGET_INFO.concat(widgetId), WidgetInfo.class, 200);
    }
}
