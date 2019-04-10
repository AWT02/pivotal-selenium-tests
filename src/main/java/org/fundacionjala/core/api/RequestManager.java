package org.fundacionjala.core.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * This manager class provides methods for manage REST requests: GET, POST, PUT,
 * and DELETE.
 */
public final class RequestManager {

    private static final RequestSpecification REQUEST = Authentication.getInstance().getRequestSpecification();

    /** Default constructor. */
    private RequestManager() {
    }

    /**
     * It processes the GET request for an endpoint url.
     * @param endpoint the endpoing url.
     * @return the response of the GET request.
     */
    public static Response getRequest(final String endpoint) {
        return given().spec(REQUEST).when().get(endpoint);
    }

    /**
     * It processes the POST request for an endpoint url.
     * @param endpoint   the endpoint url.
     * @param parameters data to be filled on the endpoint created.
     * @return the response of the POST request.
     */
    public static Response postRequest(final String endpoint, final Map<String, Object> parameters) {
        return given().spec(REQUEST).params(parameters)
                .when().post(endpoint);
    }

    /**
     * It processes the PUT request for an endpoint url.
     * @param endpoint   the endpoint url.
     * @param parameters data to be updated on the endpoint.
     * @return the response of the PUT request.
     */
    public static Response putRequest(final String endpoint, final Map<String, Object> parameters) {
        return given().spec(REQUEST).params(parameters)
                .when().put(endpoint);
    }

    /**
     * IT processes the DELETE request for an endpoint url.
     * @param endpoint the endpoint url.
     * @return the response of the DELETE request.
     */
    public static Response deleteRequest(final String endpoint) {
        return given().spec(REQUEST).when().delete(endpoint);
    }
}
