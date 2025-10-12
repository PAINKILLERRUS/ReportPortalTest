package patterns.factory_method;

public class DashboardWithErrorParameter implements Dashboard {
    private final String errorValue = "1".repeat(130);

    @Override
    public String getName() {
        return errorValue;
    }

    @Override
    public String getDescription() {
        return "Test";
    }
}
