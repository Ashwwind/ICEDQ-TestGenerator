package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
			System.out.println("Login successful.");

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Login failed.");
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
	
	@Test
	public void selectTestGeneratorModule()
	{
		WebElement testGeneratorModule = driver.findElement(By.xpath("//*[contains(text(),'Test Generator')]"));
		testGeneratorModule.click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement testGeneratorHeader = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//h1[contains(text(),'Test Generator')]")));
		
		Assert.assertTrue(testGeneratorHeader.isDisplayed(), "Test Generator page not displayed");
	    System.out.println("Test Generator page validation successful.");

	}
	
	
   
   
}