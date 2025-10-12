package data_provider;

import dto.HubDTO;
import dto.api_key.KeyNameDTO;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

import static enums.TestObjectName.API_KEY;
import static service.NameService.getUniqueApiKeyName;

public class ApiKeyDataProvider {

    @DataProvider(name = "createApiKey")
    public static Iterator<Object[]> createApiKey() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setKey(
                                new KeyNameDTO()
                                        .setName(getUniqueApiKeyName(API_KEY.getPublicName()))));

        return data
                .stream()
                .map(val -> new Object[]{val})
                .toList().iterator();
    }
}
