package steps.api;

import dto.ServerResponse;
import dto.api_key.KeyDTO;
import dto.api_key.KeyNameDTO;
import io.qameta.allure.Step;
import service.KeyService;

import java.util.List;

public class KeySteps {

    private final KeyService service = new KeyService();

    @Step("Создание и получение API Key")
    public KeyDTO createApiKey(KeyNameDTO name) {
        return service.create(name);
    }

    @Step("Удаление ключа")
    public ServerResponse deleteApiKey(Integer keyId) {
        return service.delete(keyId);
    }

    @Step("Получение списка всех ключей")
    public List<KeyDTO> getAllApiKeys() {
        return service.getAll();
    }
}
