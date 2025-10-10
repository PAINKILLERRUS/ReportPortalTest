package service;

import service.rest_assured.ApiInterface;
import service.rest_assured.Dto;

import static enums.TestObjectName.API_KEY;
import static enums.TestObjectName.DASHBOARD;

/**
 * Сервис удаления созданных в процессе тестирования объектов
 */

public class CleaningService {

    public static void deleteAllUnusedObjects() {
        deleteUnusedObjects(new DashboardService(), DASHBOARD.getPublicName());
        deleteUnusedObjects(new KeyService(), API_KEY.getPublicName());
    }

    public static void deleteUnusedObjects(ApiInterface apiInterface, String name) {
        apiInterface.getAll()
                .stream()
                .filter(object -> object.getName() != null)
                .filter(object -> object.getName().startsWith(name))
                .map(Dto::getId)
                .forEach(apiInterface::delete);
    }
}
