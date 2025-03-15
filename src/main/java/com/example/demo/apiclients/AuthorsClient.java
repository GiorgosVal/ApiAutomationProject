package com.example.demo.apiclients;

import org.springframework.stereotype.Component;

/**
 * Client for the Authors endpoint
 */
@Component
public class AuthorsClient extends CommonRestAssuredClient implements IRestAssuredClient {

    /**
     * Constructs the endpoint of the generic GET list URL (e.g. /api/Authors)
     *
     * @return the GET list URL
     */
    @Override
    public String getAllEndpoint() {
        return baseUrl + "/api/" + apiVersion + "/Authors";
    }

}
