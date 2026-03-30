package com.api.base;

import com.api.utils.ConfigReader;
import io.restassured.RestAssured;

public class Base {

	public static void setup() {

		RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

		RestAssured.useRelaxedHTTPSValidation();
	}
}