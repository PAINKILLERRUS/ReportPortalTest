package service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonService {

    public <T> T getObjectFromJson(String jsonName, Class<T> dtoClass) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/json_files/".concat(jsonName)));
        return new ObjectMapper().readValue(bufferedReader, dtoClass);
    }
}
