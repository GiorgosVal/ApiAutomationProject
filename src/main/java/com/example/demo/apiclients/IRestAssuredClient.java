package com.example.demo.apiclients;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.annotation.Nullable;

import static io.restassured.RestAssured.given;

/**
 * Interface declaring default RestAssured methods to contact with an endpoint
 */
public interface IRestAssuredClient {

    /**
     * Method to me overridden by the implementing members. It should return the endpoint
     * of the generic GET list URL (e.g. /api/Authors)
     *
     * @return the GET list URL
     */
    String getAllEndpoint();

    /**
     * Constructs the URL endpoint of a GET by id request (e.g. /api/Authors/{id})
     *
     * @param id The id of the resource
     * @return the GET by id URL
     */
    default String getByIdEndpoint(String id) {
        return getAllEndpoint() + "/" + id;
    }

    /**
     * Performs a GET request and extracts the response
     *
     * @param contentType The content type to use to perform the request
     * @return The extracted response
     */
    default Response getAll(ContentType contentType) {
        return given()
                .log().all()
                .accept(contentType)
                .when()
                .get(getAllEndpoint()).
                then().log().all().extract().response();
    }

    /**
     * Performs a GET by id request and extracts the response
     *
     * @param contentType The content type to use to perform the request
     * @param id          The id of the resource
     * @return The extracted response
     */
    default Response getById(ContentType contentType, String id) {
        return given()
                .log().all()
                .accept(contentType)
                .when()
                .get(getByIdEndpoint(id))
                .then().log().all().extract().response();
    }

    /**
     * Performs a POST request and extracts the response
     *
     * @param contentType The content type to use to perform the request
     * @param object      The object to post inside the request body
     * @return The extracted response
     */
    default Response post(ContentType contentType, @Nullable Object object) {
        return given()
                .log().all()
                .accept(contentType)
                .contentType(contentType)
                .body(new Gson().toJson(object))
                .when()
                .post(getAllEndpoint())
                .then().log().all().extract().response();
    }

    /**
     * Performs a PUT request and extracts the response
     *
     * @param contentType The content type to use to perform the request
     * @param id          The id of the resource
     * @param object      The object to post inside the request body
     * @return The extracted response
     */
    default Response put(ContentType contentType, String id, @Nullable Object object) {
        return given()
                .log().all()
                .accept(contentType)
                .contentType(contentType)
                .body(new Gson().toJson(object))
                .when()
                .put(getByIdEndpoint(id))
                .then().log().all().extract().response();
    }

    /**
     * Performs a DELETE request and extracts the response
     *
     * @param id The id of the resource
     * @return The extracted response
     */
    default Response delete(String id) {
        return given()
                .log().all()
                .accept(ContentType.ANY)
                .when()
                .delete(getByIdEndpoint(id))
                .then().log().all().extract().response();
    }


}
