package com.qa.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentReportListener;
import com.qa.pages.LoginPage;

@Listeners(ExtentReportListener.class)
public class LoginTest extends Base {

	String baseUrl = ConfigReader.getProperty("baseUrl");

	@Test
	public void validateLoginPage() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginPageValidation();
	}

	@Test()
	public void Login() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
	}
}
