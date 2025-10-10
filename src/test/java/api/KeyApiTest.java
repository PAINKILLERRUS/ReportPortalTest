package api;

import dto.ServerResponse;
import dto.api_key.KeyDTO;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.api.KeySteps;

import java.util.List;
import java.util.stream.Collectors;

import static api.Utils.createAPIKey;
import static enums.ServerMessage.DELETE_API_KEY_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static service.CleaningService.deleteAllUnusedObjects;

@Epic("API")
@Feature("Api Key")
public class KeyApiTest {

    private final KeySteps keyStep = new KeySteps();

    @AfterAll
    public static void deleteObjects() {
        deleteAllUnusedObjects();
    }

    @Test
    @Owner("Антипов Иван")
    @Story("Получение ключа")
    @DisplayName("Получение ключа")
    public void testGetKey() {
        KeyDTO key = createAPIKey();

        Allure.addAttachment("Key name: ", key.getName());
        Allure.addAttachment("Key id: ", String.valueOf(key.getId()));
        assertNotNull(key.getName(), "Проверка на наличие имени у созданного ключа");
        assertNotNull(key.getId(), "Проверка на наличие Id у созданного ключа");
    }

    @Test
    @Owner("Антипов Иван")
    @Story("Удаление ключа")
    @DisplayName("Удаление ключа")
    public void testDeleteKey() {
        KeyDTO key = createAPIKey();
        ServerResponse deleteResponse = keyStep.deleteApiKey(key.getId());

        String messageAboutDeletion = DELETE_API_KEY_RESPONSE.getMessage().replace("{id}", String.valueOf(key.getId()));

        assertEquals(messageAboutDeletion, deleteResponse.getMessage(), "Соответствие информативного сообщения об удалении API KEY");
    }

    @Test
    @Owner("Антипов Иван")
    @Story("Получение списка всех созданных ключей")
    @DisplayName("Получение списка всех созданных ключей")
    public void testGettingAListOfAllCreatedKeys() {
        KeyDTO key1 = createAPIKey();
        KeyDTO key2 = createAPIKey();
        KeyDTO key3 = createAPIKey();

        List<KeyDTO> apiKeysList = keyStep.getAllApiKeys();

        Allure.addAttachment("Key1 name: ", key1.getName());
        Allure.addAttachment("Key2 name: ", key2.getName());
        Allure.addAttachment("Key3 name: ", key3.getName());
        assertNotNull(apiKeysList.stream().map(KeyDTO::getId).collect(Collectors.toSet()), "Проверка наличия ID у созданных ключей в общем списке");
    }
}
