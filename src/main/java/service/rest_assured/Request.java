package service.rest_assured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.NonNull;
import specifications.Specification;

import java.util.Map;

import static enums.HttpMethod.*;
import static io.restassured.RestAssured.given;

public class Request {

    public Response executeRequest(String endpoint, String method, Object bodyPayload, Map<String, ?> params, int statusCode) {
        RequestSpecification requestSpecification = given()
                .header("Accept", "application/json")
                .header("Authorization", JWTTokenManager.getInstance().getJwtToken())
                .spec(Specification.requestSpecification())
                .basePath(endpoint);
        if (params != null) {
            requestSpecification.queryParams(params);
        }
        if (bodyPayload != null) {
            requestSpecification.body(bodyPayload);
        }
        return requestSpecification
                .when()
                .request(method)
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract().response();
    }

    public <T> T get(@NonNull String basePath, @NonNull Class<T> dtoClass) {
        Response response = executeRequest(basePath, GET.name(), null, null, 200);
        return response.as(dtoClass);
    }

    public Response get(@NonNull String basePath, @NonNull Integer statusCode) {
        return executeRequest(basePath, GET.name(), null, null, statusCode);
    }

    public <T> T get(@NonNull String basePath, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        Response response = executeRequest(basePath, GET.name(), null, null, statusCode);
        return response.as(dtoClass);
    }

    public <T> T post(@NonNull String basePath, @NonNull Object bodyPayLoad, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        Response response = executeRequest(basePath, POST.name(), bodyPayLoad, null, statusCode);
        return response.as(dtoClass);
    }

    public <T> T post(@NonNull String basePath, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        Response response = executeRequest(basePath, POST.name(), null, null, statusCode);
        return response.as(dtoClass);
    }

    public <T> T post(@NonNull String basePath, @NonNull Map<String, ?> params, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        Response response = executeRequest(basePath, POST.name(), null, params, statusCode);
        return response.as(dtoClass);
    }

    public <T> T put(@NonNull String basePath, @NonNull Object bodyPayLoad, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        Response response = executeRequest(basePath, PUT.name(), bodyPayLoad, null, statusCode);
        return response.as(dtoClass);
    }

    public <T> T delete(@NonNull String basePath, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
        Response response = executeRequest(basePath, DELETE.name(), null, null, statusCode);
        return response.as(dtoClass);
    }
}
