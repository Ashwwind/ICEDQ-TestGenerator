package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.Base;
import config.ConfigReader;
import pages.LoginPage;

public class LoginTest extends Base {

	// String baseUrl = "https://192.168.100.102:32222/";
	String baseUrl = ConfigReader.getProperty("baseUrl");

	@Test(dependsOnMethods = { "validateLoginPage" })
	public void testValidLogin() {
<<<<<<< HEAD
=======

>>>>>>> 496fe17ab67b67ab6d96799d149c3e1545ff0060
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

	}

	@Test
	public void validateLoginPage() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginPageValidation();	
	}

	@Test
	public void selectTestGeneratorModule() {
		WebElement testGeneratorModule = driver.findElement(By.xpath("//*[contains(text(),'Test Generator')]"));
		testGeneratorModule.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement testGeneratorHeader = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Test Generator')]")));

		Assert.assertTrue(testGeneratorHeader.isDisplayed(), "Test Generator page not displayed");
		System.out.println("Test Generator page validation successful.");

//	@Test
//	public void validateInvalidLoginError()
//	{
//		WebElement usernameField = driver.findElement(By.id("username"));
//        WebElement passwordField = driver.findElement(By.id("password"));
//        WebElement signInButton = driver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
//
//        usernameField.clear();
//        usernameField.sendKeys("wrong_user");
//        passwordField.clear();
//        passwordField.sendKeys("wrong_pass");
//        signInButton.click();
//
//        // Wait briefly for error message (can replace with WebDriverWait)
//        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
//
//        // Validate error message
//        WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Invalid username or password') or contains(@class,'alert')]"));
//        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed after invalid login.");
//		
//	}

	}
}