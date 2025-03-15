package com.example.demo.business;

import com.example.demo.apiclients.IRestAssuredClient;
import com.example.demo.dtos.AbstractApiDTO;
import com.example.demo.dtos.ErrorResponseDTO;
import com.example.demo.utils.SerializationUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;

/**
 * Generic Business Object, providing default business implementations, and exposing abstract methods
 * for the concrete subclasses to implement
 *
 * @param <T>
 */
public abstract class GenericApiBO<T extends AbstractApiDTO> {

    private final IRestAssuredClient iRestAssuredClient;

    private static final String GENERIC_ERROR_SCHEMA = "jsonschemas/error_generic_json_schema.json";
    private static final String SPECIFIC_ERROR_SCHEMA = "jsonschemas/error_specific_json_schema.json";

    @Autowired
    public GenericApiBO(@Qualifier("specificClient") IRestAssuredClient client) {
        this.iRestAssuredClient = client;
    }

    /* ---------------------------------------------------------------------------------- */
    /* -------------------------------- ABSTRACT METHODS -------------------------------- */
    /* ---------------------------------------------------------------------------------- */

    /**
     * @return The path of the JSON schema that will be used to verify responses that contain a list of the {@link T} DTO
     */
    abstract String getAllSchema();

    /**
     * @return The path of the JSON schema that will be used to verify responses that contain a single {@link T} DTO
     */
    abstract String getSingleSchema();

    /**
     * Verifies that the two objects are equal
     *
     * @param actual   The object to verify against the expected
     * @param expected The object that will be used as reference
     */
    abstract void verifyDto(T actual, T expected);

    /**
     * Method that provides a new dummy instance of the {@link T} DTO
     *
     * @return a new instance
     */
    abstract T dummyDto();

    /**
     * Method that is expected to provide a key/value pair (error key/error message)
     *
     * @param dto an instance of the {@link T} DTO that will be used to generate the expected pair
     * @return the expected pair of error key/error message
     */
    abstract ImmutablePair<String, String> getExpectedErrorMessage(T dto);

    /* ----------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------- */

    /**
     * Verifies the status code of the response and that the response body matches the JSON schema
     *
     * @param response       The response to verify
     * @param statusCode     The expected status code
     * @param jsonSchemaPath The path of the expected JSON schema
     * @return a {@link ValidatableResponse} to be used in subsequent assertions
     */
    public ValidatableResponse verifyStatusCodeAndJsonSchema(Response response, int statusCode, String jsonSchemaPath) {
        return response.then().statusCode(statusCode).body(matchesJsonSchemaInClasspath(jsonSchemaPath));
    }

    /* ----------------------------------------------------------------------------- */
    /* -------------------------------- HAPPY PATHS -------------------------------- */
    /* ----------------------------------------------------------------------------- */

    /**
     * Performs a GET (all) request, verifies the status code and JSON schema and deserializes the response body
     * to the wanted type
     *
     * @param tClass The class to use for deserialization
     * @return A list of the DTOs
     */
    public List<T> getAllAndVerifyResponseCodeAndJsonSchema(Class<T> tClass) {
        Response response = iRestAssuredClient.getAll(ContentType.JSON);
        verifyStatusCodeAndJsonSchema(response, 200, getAllSchema());
        return SerializationUtils.deserializeJsonToList(response.body().asString(), tClass);
    }

    /**
     * Performs a GET (by id) request, verifies the status code and JSON schema and deserializes the response body
     * to the wanted type
     *
     * @param tClass The class to use for deserialization
     * @return A single DTOs
     */
    public T getByIdAndVerifyResponseCodeAndJsonSchema(String id, Class<T> tClass) {
        Response response = iRestAssuredClient.getById(ContentType.JSON, id);
        verifyStatusCodeAndJsonSchema(response, 200, getSingleSchema());
        return SerializationUtils.deserializeJsonToObject(response.body().asString(), tClass);
    }

    /**
     * Performs a POST request, verifies the status code and JSON schema and deserializes the response body
     * to the wanted type
     *
     * @param dtoToPost The object to serialize inside the request body
     * @param tClass    The class to use for deserialization
     * @return A single DTOs
     */
    private T postAndVerifyResponseCodeAndJsonSChema(T dtoToPost, Class<T> tClass) {
        Response response = iRestAssuredClient.post(ContentType.JSON, dtoToPost);
        verifyStatusCodeAndJsonSchema(response, 200, getSingleSchema());
        return SerializationUtils.deserializeJsonToObject(response.body().asString(), tClass);
    }


    /**
     * Performs a PUT request, verifies the status code and JSON schema and deserializes the response body
     * to the wanted type
     *
     * @param id       The id of the resource against which the PUT request will take place
     * @param dtoToPut The object to serialize inside the request body
     * @param tClass   The class to use for deserialization
     * @return A single DTOs
     */
    private T putAndVerifyResponseCodeAndJsonSChema(String id, T dtoToPut, Class<T> tClass) {
        Response response = iRestAssuredClient.put(ContentType.JSON, id, dtoToPut);
        verifyStatusCodeAndJsonSchema(response, 200, getSingleSchema());
        return SerializationUtils.deserializeJsonToObject(response.body().asString(), tClass);
    }

    /**
     * Performs a DELETE request, verifies the status code and that the response body is empty
     *
     * @param id The id of the resource against which the DELETE request will take place
     */
    public void deleteByIdAndVerifyResponseCode(String id) {
        iRestAssuredClient.delete(id)
                .then()
                .log().all()
                .statusCode(200)
                .body(is(emptyOrNullString()));
    }


    /**
     * Performs a POST request, verifies the status code, the JSON schema and the actual values of the response body
     *
     * @param dtoToPost The object to serialize inside the request body
     * @param tClass    The class to use for deserialization
     */
    public void postAndVerifyResponseCodeJsonSchemaAndBody(T dtoToPost, Class<T> tClass) {
        T actualDto = postAndVerifyResponseCodeAndJsonSChema(dtoToPost, tClass);
        verifyDto(actualDto, dtoToPost);
    }

    /**
     * Performs a PUT request, verifies the status code, the JSON schema and the actual values of the response body
     *
     * @param id       The id of the resource against which the PUT request will take place
     * @param dtoToPut The object to serialize inside the request body
     * @param tClass   The class to use for deserialization
     */
    public void putAndVerifyResponseCodeJsonSchemaAndBody(String id, T dtoToPut, Class<T> tClass) {
        T actualDto = putAndVerifyResponseCodeAndJsonSChema(id, dtoToPut, tClass);
        verifyDto(actualDto, dtoToPut);
    }

    /* -------------------------------------------------------------------------------- */
    /* -------------------------------- NEGATIVE PATHS -------------------------------- */
    /* -------------------------------------------------------------------------------- */

    /**
     * Performs a POST request, verifies the status code and the JSON schema and extracts the response body as {@link ErrorResponseDTO}
     *
     * @param dtoToPost      The object to serialize inside the request body
     * @param statusCode     The expected status code
     * @param jsonSchemaPath The path of the expected JSON schema
     * @return the error response
     */
    private ErrorResponseDTO postAndVerifyErrorResponseCodeAndJsonSChema(T dtoToPost, int statusCode, String jsonSchemaPath) {
        return verifyStatusCodeAndJsonSchema(iRestAssuredClient.post(ContentType.JSON, dtoToPost), statusCode, jsonSchemaPath)
                .extract().as(ErrorResponseDTO.class);
    }

    /**
     * Performs a PUT request, verifies the status code and the JSON schema and extracts the response body as {@link ErrorResponseDTO}
     *
     * @param id             The id of the resource against which the PUT request will take place
     * @param dtoToPut       The object to serialize inside the request body
     * @param statusCode     The expected status code
     * @param jsonSchemaPath The path of the expected JSON schema
     * @return the error response
     */
    private ErrorResponseDTO putAndVerifyErrorResponseCodeAndJsonSChema(String id, T dtoToPut, int statusCode, String jsonSchemaPath) {
        return verifyStatusCodeAndJsonSchema(iRestAssuredClient.put(ContentType.JSON, id, dtoToPut), statusCode, jsonSchemaPath)
                .extract().as(ErrorResponseDTO.class);
    }

    /**
     * Performs a GET (by id) request, verifies the status code and the JSON schema and extracts the response body as {@link ErrorResponseDTO}
     *
     * @param id             The id of the resource against which the GET request will take place
     * @param statusCode     The expected status code
     * @param jsonSchemaPath The path of the expected JSON schema
     * @return the error response
     */
    private ErrorResponseDTO getByIdAndVerifyErrorResponseCodeAndJsonSchema(String id, int statusCode, String jsonSchemaPath) {
        return verifyStatusCodeAndJsonSchema(iRestAssuredClient.getById(ContentType.JSON, id), statusCode, jsonSchemaPath)
                .extract().as(ErrorResponseDTO.class);
    }

    /**
     * Performs a DELETE request, verifies the status code and the JSON schema and extracts the response body as {@link ErrorResponseDTO}
     *
     * @param id             The id of the resource against which the DELETE request will take place
     * @param statusCode     The expected status code
     * @param jsonSchemaPath The path of the expected JSON schema
     * @return the error response
     */
    private ErrorResponseDTO deleteByIdAndVerifyErrorResponseCodeAndJsonSchema(String id, int statusCode, String jsonSchemaPath) {
        return verifyStatusCodeAndJsonSchema(iRestAssuredClient.delete(id), statusCode, jsonSchemaPath)
                .extract().as(ErrorResponseDTO.class);
    }

    /**
     * Verifies the title and the status code properties of the {@link ErrorResponseDTO}
     *
     * @param errorResponseDTO The error response to verify
     * @param statusCode       The status code inside the response to verify
     * @param title            The title inside the response to verify
     * @return a {@link SoftAssert} to be used for subsequent assertions
     */
    private SoftAssert verifyTitleAndStatusInsideErrorResponse(ErrorResponseDTO errorResponseDTO, int statusCode, String title) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errorResponseDTO.getTitle().equalsIgnoreCase(title), "Wrong title inside response body:");
        softAssert.assertEquals(errorResponseDTO.getStatus(), statusCode, "Wrong status inside response body");
        return softAssert;
    }

    /**
     * Verifies the error message inside the {@link ErrorResponseDTO}
     *
     * @param softAssert       The already initialized soft assertions
     * @param errorResponseDTO The error response to verify
     * @param errorKey         The key of the error inside the error response
     * @param errorMessage     The error message inside the error response to verify
     * @return The same {@link SoftAssert} to be used for subsequent assertions
     */
    private SoftAssert verifyErrorInsideErrorResponse(SoftAssert softAssert, ErrorResponseDTO errorResponseDTO, String errorKey, String errorMessage) {
        softAssert.assertNotNull(errorResponseDTO.getErrors(), "errors should not be null");
        softAssert.assertTrue(errorResponseDTO.getErrors().get(errorKey).get(0).contains(errorMessage), "Wrong error message");
        return softAssert;
    }

    /**
     * Performs a GET (by id) request with a supposed non-existing id, and verifies the status code (404) and the title (Not Found)
     * inside the error response ({@link ErrorResponseDTO})
     *
     * @param id the id to use to perform the GET request
     */
    public void verifyGetByIdNotFound(String id) {
        ErrorResponseDTO errorResponseDTO = getByIdAndVerifyErrorResponseCodeAndJsonSchema(id, 404, GENERIC_ERROR_SCHEMA);
        verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 404, "Not Found")
                .assertAll();
    }

    /**
     * Performs a PUT request with a supposed non-existing id, and verifies the status code (404) and the title (Not Found)
     * inside the error response ({@link ErrorResponseDTO})
     *
     * @param id the id to use to perform the PUT request
     */
    public void verifyPutByIdNotFound(String id) {
        ErrorResponseDTO errorResponseDTO = putAndVerifyErrorResponseCodeAndJsonSChema(id, dummyDto(), 404, GENERIC_ERROR_SCHEMA);
        verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 404, "Not Found")
                .assertAll();
    }

    /**
     * Performs a DELETE request with a supposed non-existing id, and verifies the status code (404) and the title (Not Found)
     * inside the error response ({@link ErrorResponseDTO})
     *
     * @param id the id to use to perform the DELETE request
     */
    public void verifyDeleteByIdNotFound(String id) {
        ErrorResponseDTO errorResponseDTO = deleteByIdAndVerifyErrorResponseCodeAndJsonSchema(id, 404, GENERIC_ERROR_SCHEMA);
        verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 404, "Not Found")
                .assertAll();
    }

    /**
     * Performs a GET (by id) request with a supposed not valid id, and verifies the status code (400) and the title,
     * and the error message inside the error response ({@link ErrorResponseDTO})
     *
     * @param id the id to use to perform the GET request
     */
    public void verifyGetByIdNotValid(String id) {
        ErrorResponseDTO errorResponseDTO = getByIdAndVerifyErrorResponseCodeAndJsonSchema(id, 400, SPECIFIC_ERROR_SCHEMA);
        SoftAssert softAssert = verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 400, "One or more validation errors occurred.");
        verifyErrorInsideErrorResponse(softAssert, errorResponseDTO, "id", String.format("The value '%s' is not valid.", id))
                .assertAll();
    }

    /**
     * Performs a PUT request with a supposed not valid id, and verifies the status code (400) and the title,
     * and the error message inside the error response ({@link ErrorResponseDTO})
     *
     * @param id the id to use to perform the PUT request
     */
    public void verifyPutByIdNotValid(String id) {
        ErrorResponseDTO errorResponseDTO = putAndVerifyErrorResponseCodeAndJsonSChema(id, dummyDto(), 400, SPECIFIC_ERROR_SCHEMA);
        SoftAssert softAssert = verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 400, "One or more validation errors occurred.");
        verifyErrorInsideErrorResponse(softAssert, errorResponseDTO, "id", String.format("The value '%s' is not valid.", id))
                .assertAll();
    }

    /**
     * Performs a DELETE request with a supposed not valid id, and verifies the status code (400) and the title,
     * and the error message inside the error response ({@link ErrorResponseDTO})
     *
     * @param id the id to use to perform the DELETE request
     */
    public void verifyDeleteByIdNotValid(String id) {
        ErrorResponseDTO errorResponseDTO = deleteByIdAndVerifyErrorResponseCodeAndJsonSchema(id, 400, SPECIFIC_ERROR_SCHEMA);
        SoftAssert softAssert = verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 400, "One or more validation errors occurred.");
        verifyErrorInsideErrorResponse(softAssert, errorResponseDTO, "id", String.format("The value '%s' is not valid.", id))
                .assertAll();
    }


    /**
     * Performs a POST request with a supposed not valid DTO, and verifies the status code (400) and the title,
     * and the error message inside the error response ({@link ErrorResponseDTO})
     *
     * @param dtoToPost The invalid object to serialize inside the request body
     */
    public void verifyPostWithInvalidDto(T dtoToPost) {
        ImmutablePair<String, String> errors = getExpectedErrorMessage(dtoToPost);
        ErrorResponseDTO errorResponseDTO = postAndVerifyErrorResponseCodeAndJsonSChema(dtoToPost, 400, SPECIFIC_ERROR_SCHEMA);
        SoftAssert softAssert = verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 400, "One or more validation errors occurred.");
        verifyErrorInsideErrorResponse(softAssert, errorResponseDTO, errors.getKey(), errors.getValue())
                .assertAll();
    }

    /**
     * Performs a PUT request with a supposed not valid DTO, and verifies the status code (400) and the title,
     * and the error message inside the error response ({@link ErrorResponseDTO})
     *
     * @param id       the id to use to perform the PUT request
     * @param dtoToPut The invalid object to serialize inside the request body
     */
    public void verifyPutWithInvalidDto(String id, T dtoToPut) {
        ImmutablePair<String, String> errors = getExpectedErrorMessage(dtoToPut);
        ErrorResponseDTO errorResponseDTO = putAndVerifyErrorResponseCodeAndJsonSChema(id, dtoToPut, 400, SPECIFIC_ERROR_SCHEMA);
        SoftAssert softAssert = verifyTitleAndStatusInsideErrorResponse(errorResponseDTO, 400, "One or more validation errors occurred.");
        verifyErrorInsideErrorResponse(softAssert, errorResponseDTO, errors.getKey(), errors.getValue())
                .assertAll();
    }
}
