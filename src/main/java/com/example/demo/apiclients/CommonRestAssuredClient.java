package com.example.demo.apiclients;

import org.springframework.beans.factory.annotation.Value;

/**
 * Abstract client defining common properties
 */
public abstract class CommonRestAssuredClient {

    /**
     * The base url of the API
     */
    @Value("${api.base.url}")
    String baseUrl;

    /**
     * The version of the API
     */
    @Value("${api.version}")
    String apiVersion;
}
