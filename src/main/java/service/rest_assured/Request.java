package service.rest_assured;

import io.restassured.response.Response;
import lombok.NonNull;
import specifications.Specification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Request {

    private final JWTTokenManager jwtTokenManager = new JWTTokenManager();

    public <T> T get(@NonNull String basePath, @NonNull Class<T> dtoClass) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(200))
                .extract().body().as(dtoClass);
    }

    public Response get(@NonNull String basePath, @NonNull Integer statusCode) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract().response();
    }

    public <T> T get(@NonNull String basePath, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract().body().as(dtoClass);
    }

    public <T> T post(@NonNull String basePath, @NonNull Object bodyPayLoad, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .body(bodyPayLoad)
                .when()
                .post()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }

    public <T> T post(@NonNull String basePath, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .post()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }

    public <T> T post(@NonNull String basePath, @NonNull Map<String, ?> params, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .params(params)
                .when()
                .post()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }

    public <T> T put(@NonNull String basePath, @NonNull Object bodyPayLoad, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .body(bodyPayLoad)
                .when()
                .put()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }

    public <T> T delete(@NonNull String basePath, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        return given()
                .headers("Authorization", jwtTokenManager.getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .delete()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }
}
