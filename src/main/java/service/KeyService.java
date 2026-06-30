package service;

import dto.ServerResponse;
import dto.api_key.AllKeysDTO;
import dto.api_key.KeyDTO;
import service.rest_assured.Request;
import service.rest_assured.crud_interfaces.Create;
import service.rest_assured.crud_interfaces.GetAllAndDelete;

import java.util.List;

public class KeyService implements GetAllAndDelete, Create {

    private static final String GET_KEY = "/api/users/2/api-keys";
    private static final String DELETE_KEY = "/api/users/2/api-keys/";
    private static final String GET_ALL_KEYS = "/api/users/2/api-keys";


    @Override
    public ServerResponse delete(int id) {
        return new Request().delete(DELETE_KEY.concat(String.valueOf(id)), ServerResponse.class, 200);
    }

    @Override
    public <T> KeyDTO create(T name) {
        return new Request().post(GET_KEY, name, KeyDTO.class, 201);
    }

    @Override
    public List<KeyDTO> getAll() {
        AllKeysDTO keysDTO = new Request().get(GET_ALL_KEYS, AllKeysDTO.class, 200);
        return keysDTO.getItems();
    }
}
