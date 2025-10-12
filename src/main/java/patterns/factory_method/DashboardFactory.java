package patterns.factory_method;

/**
 * Класс реализующий паттерн Фабричный метод (Factory Method)
 */
public class DashboardFactory {

    public static Dashboard createDashboard(String type) {
        return switch (type.toLowerCase()) {
            case "standard" -> new StandardDashboard();
            case "empty" -> new DashboardWithEmptyName();
            case "error" -> new DashboardWithErrorParameter();
            default -> throw new IllegalArgumentException("Unknown Dashboard type");
        };
    }
}
