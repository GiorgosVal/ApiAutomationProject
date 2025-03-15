package com.example.demo.apiclients;

import org.springframework.stereotype.Component;

/**
 * Client for the Books endpoint
 */
@Component
public class BooksClient extends CommonRestAssuredClient implements IRestAssuredClient {

    /**
     * Constructs the endpoint of the generic GET list URL (e.g. /api/Books)
     *
     * @return the GET list URL
     */
    @Override
    public String getAllEndpoint() {
        return baseUrl + "/api/" + apiVersion + "/Books";
    }
}
