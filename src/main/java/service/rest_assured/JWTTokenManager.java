package service.rest_assured;

import configuration.ConfigReader;
import dto.TokenDTO;
import io.restassured.http.ContentType;
import specifications.Specification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static service.ApiService.GET_TOKEN;

public class JWTTokenManager {
    private String jwtToken;
    private ConfigReader reader;

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
