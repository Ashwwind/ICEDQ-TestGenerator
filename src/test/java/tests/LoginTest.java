package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import config.ConfigReader;
import pages.LoginPage;

public class LoginTest extends Base {

	@BeforeClass

	@Test
	public void testValidLogin() {
		try {

			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
			Thread.sleep(5000);
			loginPage.clickLogin();

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

	@Test
	public void validateDashboardPage() throws InterruptedException {
		// Validate welcome message
		WebElement welcomeMessage = driver.findElement(By.xpath("//*[contains(text(),'Hi Ashwin, welcome to iceDQ')]"));
		Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message not displayed");

		// Validate dashboard icons
		String[] expectedModules = { "Data testing", "BI Report testing", "Test Generator", "Dashboard", "Connectors",
				"Administration", "Scheduler" };

		for (String module : expectedModules) {
			WebElement moduleElement = driver.findElement(By.xpath("//*[contains(text(),'" + module + "')]"));
			Assert.assertTrue(moduleElement.isDisplayed(), module + " not displayed");
		}
		Thread.sleep(500);

		System.out.println("Dashboard validation successful.");
	}

}