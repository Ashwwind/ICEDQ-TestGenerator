package com.api.pages;

import com.api.client.APIClient;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class UserAPI {

	public static Response createUser(String userName, String firstName, String lastName, String email,
			String password) {

		Map<String, Object> payload = new HashMap<>();

		payload.put("userName", userName);
		payload.put("firstName", firstName);
		payload.put("lastName", lastName);
		payload.put("email", email);
		payload.put("tempCredential", password);

		Response response = APIClient.getRequest()

				.header("accept", "application/json").header("org-id", "org-icedq.qa")

				.body(payload)

				.post("/api/v1/users");

		return response;
	}
}