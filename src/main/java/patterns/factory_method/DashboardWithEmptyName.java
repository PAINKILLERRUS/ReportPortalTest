package patterns.factory_method;

public class DashboardWithEmptyName implements Dashboard {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Test";
    }
}
