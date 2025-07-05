package data_provider;

import dto.HubDTO;
import dto.dashboard.DashboardItemDTO;
import dto.widget.WidgetDTO;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

import static enums.TestObjectName.DASHBOARD;
import static enums.TestObjectName.WIDGET_NAME;
import static service.NameService.getUniqueDashboardName;
import static service.NameService.getWidgetName;

public class DashboardDataProvider {

    @DataProvider(name = "createDashboard")
    public static Iterator<Object[]> createDashboard() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setDashboardItemDTO(
                                new DashboardItemDTO()
                                        .setName(getUniqueDashboardName(DASHBOARD.getPublicName()))
                                        .setDescription("Test")));

        return data
                .stream()
                .map(val -> new Object[]{val})
                .toList().iterator();
    }

    @DataProvider(name = "createDashboardAndWidget")
    public static Iterator<Object[]> createDashboardAndWidget() {

        List<HubDTO> data = List.of(
                new HubDTO()
                        .setDashboardItemDTO(
                                new DashboardItemDTO()
                                        .setName(getUniqueDashboardName(DASHBOARD.getPublicName()))
                                        .setDescription("Test"))
                        .setWidget(
                                new WidgetDTO()
                                        .setName(getWidgetName(WIDGET_NAME.getPublicName()))
                                        .setDescription("Test")));

        return data
                .stream()
                .map(val -> new Object[]{val})
                .toList().iterator();
    }
}
