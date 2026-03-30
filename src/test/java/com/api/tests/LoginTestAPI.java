package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.Base;
import com.api.pages.LoginAPI;

public class LoginTestAPI extends Base {

	@Test
	public void loginTest() {

		setup();

		String token = LoginAPI.getAccessToken();

		System.out.println("Access Token: " + token);

		Assert.assertNotNull(token);
	}
}