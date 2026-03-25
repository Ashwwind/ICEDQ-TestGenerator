package com.api.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager {

	private static String accessToken;
	private static long expiryTime;

	public static String getToken() {

		if (accessToken == null || System.currentTimeMillis() > expiryTime) {

			System.out.println("Generating new token...");

			Response response = given().header("Content-Type", "application/x-www-form-urlencoded")
					.formParams(PayloadBuilder.tokenPayload()).when().post(ConfigReader.getProperty("tokenEndpoint"));

			response.prettyPrint();

			accessToken = response.jsonPath().getString("access_token");

			int expiresIn = response.jsonPath().getInt("expires_in");

			expiryTime = System.currentTimeMillis() + (expiresIn * 1000) - 5000;
		}

		return accessToken;
	}
}