package service.rest_assured;

import dto.TokenDTO;
import io.restassured.http.ContentType;
import specifications.Specification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static service.DashboardService.GET_TOKEN;

/**
 * Класс, отвечающий за получение JWT токена. В классе реализуется паттерн Singleton(одиночка)
 */

public class JWTTokenManager {

    /**
     * Единственный экземпляр(инициализируется при первом вызове getInstance())
     */
    private static JWTTokenManager instance;

    private String jwtToken;


    /**
     * Приватный конструктор предотвращает создание экземпляров извне
     */
    private JWTTokenManager() {

    }

    /**
     * Глобальная точка доступа
     */
    public static JWTTokenManager getInstance() {
        if (instance == null) {
            instance = new JWTTokenManager();
        }
        return instance;
    }

    public String getJwtToken() {
        if (jwtToken == null) {
            jwtToken = fetchJWTToken();
        }
        return "bearer " + jwtToken;
    }


    private String fetchJWTToken() {
        TokenDTO response = given()
                .spec(Specification.requestSpecification())
                .contentType(ContentType.URLENC)
                .basePath(GET_TOKEN)
                .params(registrationParams())
                .header("Authorization", "Basic dWk6dWltYW4=")
                .when()
                .post()
                .then()
                .spec(Specification.responseSpecification(200))
                .extract()
                .body().as(TokenDTO.class);
        jwtToken = response.getAccess_token();
        return jwtToken;
    }

    public Map<String, String> registrationParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("username", "default");
        headers.put("password", "1q2w3e");
        headers.put("grant_type", "password");
        return headers;
    }
}
