package tests;

import org.testng.annotations.Test;
import base.Base;
import config.ConfigReader;
import pages.LoginPage;

public class LoginTest extends Base {

	LoginPage loginPage = new LoginPage(driver);

	@Test(dependsOnMethods = { "validateLoginPage" })
	public void testValidLogin() {
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
	}

	@Test
	public void validateLoginPage() {
		loginPage.loginPageValidation();
	}

	@Test
	public void selectTestGeneratorModule() {
		loginPage.clickTestGeneratorModuleFromHome();
	}
}