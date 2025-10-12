package patterns.factory_method;

import static enums.TestObjectName.DASHBOARD;
import static service.NameService.getUniqueDashboardName;

public class StandardDashboard implements Dashboard {
    @Override
    public String getName() {
        return getUniqueDashboardName(DASHBOARD.getPublicName());
    }

    @Override
    public String getDescription() {
        return "Test";
    }
}
