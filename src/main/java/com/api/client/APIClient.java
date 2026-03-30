package com.api.client;

import com.api.utils.TokenManager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class APIClient {

    private static RequestSpecification requestSpec;

    public static RequestSpecification getRequest() {

        if (requestSpec == null) {

            requestSpec = new RequestSpecBuilder()

                    .setRelaxedHTTPSValidation()

                    .addHeader("Authorization", "Bearer " + TokenManager.getToken())
                    .addHeader("Content-Type", "application/json")

                    // Logging
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())

                    .build();
        }

        return given().spec(requestSpec);
    }
}