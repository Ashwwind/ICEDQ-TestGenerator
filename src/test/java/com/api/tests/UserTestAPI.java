package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.Base;
import com.api.pages.UserAPI;

import io.restassured.response.Response;

public class UserTestAPI extends Base {

	@Test
	public void createUserTest() {

		setup();

		String userName = "ADTest" + System.currentTimeMillis();
		String email = userName + "@gmail.com";

		Response response = UserAPI.createUser(userName, "AD", "Test", email, "Admin@123");

		response.prettyPrint();

		System.out.println("Status Code: " + response.getStatusCode());

		Assert.assertEquals(response.getStatusCode(), 200);
	}
}