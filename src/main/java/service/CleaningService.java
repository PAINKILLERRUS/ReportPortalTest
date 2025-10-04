package service;

import service.rest_assured.Dto;

import static enums.TestObjectName.API_KEY;

/**
 * Сервис удаления созданных в процессе тестирования объектов
 */

public class CleaningService {

    public static void deleteAllUnusedObjects() {
        // deleteUnusedObjects(new ApiService(), DASHBOARD.getPublicName());
        deleteUnusedObjects(new KeyService(), API_KEY.getPublicName());
        //deleteUnusedObjects(new ApiService(), WIDGET_NAME.getPublicName());
    }

    public static void deleteUnusedObjects(KeyService apiInterface, String name) {
        apiInterface.getAll()
                .stream()
                .filter(object -> object.getName() != null)
                .filter(object -> object.getName().startsWith(name))
                .map(Dto::getId)
                .forEach(apiInterface::delete);
    }
}
