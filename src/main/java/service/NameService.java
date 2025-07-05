package service;

import java.util.UUID;

public class NameService {
    public static String getUniqueDashboardName(String text) {
        return text.concat(" ").concat(UUID.randomUUID().toString());
    }

    public static String getUniqueApiKeyName(String text) {
        return text.concat((UUID.randomUUID().toString()));
    }

    public static String getWidgetName(String text) {
        return text.concat(" ").concat(UUID.randomUUID().toString());
    }
}
