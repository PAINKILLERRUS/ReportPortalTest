package api;

import dto.ServerResponse;
import dto.dashboard.DashboardIdDTO;
import dto.dashboard.DashboardItemDTO;
import dto.api_key.KeyDTO;
import dto.api_key.KeyNameDTO;
import steps.api.ApiSteps;

import static enums.TestObjectName.API_KEY;
import static enums.TestObjectName.DASHBOARD;
import static service.NameService.getUniqueApiKeyName;
import static service.NameService.getUniqueDashboardName;

public final class DashboardUtils {

    private static final ApiSteps STEP = new ApiSteps();

    public static DashboardIdDTO createDashboard() {
        DashboardItemDTO item = new DashboardItemDTO()
                .setDescription("Test")
                .setName(getUniqueDashboardName(DASHBOARD.getPublicName()));

        return STEP.create(item);
    }

    public static ServerResponse createDashboardWithError(String option) {
        String errorValue = "1".repeat(130);

        if (option.equals("Size")){
            DashboardItemDTO item = new DashboardItemDTO()
                    .setDescription("Test")
                    .setName(errorValue);

            return STEP.createDashboardWithError(item);

        } else if (option.equals("Empty")) {
            DashboardItemDTO item = new DashboardItemDTO()
                    .setDescription("Test")
                    .setName("");

            return STEP.createDashboardWithError(item);

        } else throw new RuntimeException("Empty option field.");
    }

    public static KeyDTO createAPIKey() {
        KeyNameDTO name = new KeyNameDTO()
                .setName(getUniqueApiKeyName(API_KEY.getPublicName()));

        return STEP.createApiKey(name);
    }
}
