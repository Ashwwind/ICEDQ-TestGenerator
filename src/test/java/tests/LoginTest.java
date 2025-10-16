package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import base.Base;
import pages.LoginPage;

public class LoginTest extends Base {
	WebDriver driver = null;

	@Test
	public void testValidLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername("ashwin.d");
		loginPage.enterPassword("Admin!123");
		loginPage.clickLogin();

		// Add assertions here to verify login success
		
		// Add assertions here to verify login success

	}

}