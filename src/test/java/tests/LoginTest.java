package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
	}

	@Test
	public void validateLoginPage() {
		driver.get(baseUrl);

		// URL validation
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains(("icedq")));

		// Title validation
		String actualTitle = driver.getTitle();
		String expectedTitle = "Sign in to iCEDQ DataOps Platform";
		Assert.assertTrue(actualTitle.equals(expectedTitle), "Title mismatch! Actual title: " + actualTitle);

		// Validate logo
		WebElement logo = driver.findElement(By.xpath("/html/body/div/div[1]/div"));
		Assert.assertTrue(logo.isDisplayed(), "Logo not displayed.");

		// Validate username and password fields
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		Assert.assertTrue(usernameField.isDisplayed() && passwordField.isDisplayed(), "Text fields not displayed.");

		// Validate checkbox
		WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
		Assert.assertTrue(checkbox.isDisplayed(), "Remember Me checkbox not found.");

		// Validate Sign In and Forgot Password
		WebElement signInButton;
		signInButton = driver.findElement(By.xpath("//input[@id='kc-login']"));
		WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Password?"));
		Assert.assertTrue(signInButton.isDisplayed(), "Sign In button not displayed.");
		Assert.assertTrue(forgotPasswordLink.isDisplayed(), "Forgot Password link not displayed.");

	}

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