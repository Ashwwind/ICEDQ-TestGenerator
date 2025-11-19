package com.qa.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.pages.LoginPage;

@Listeners(ExtentListener.class)
public class LoginTest extends Base {

	String baseUrl = ConfigReader.getProperty("baseUrl");

	@Test
	public void validateLoginPage() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginPageValidation();
	}

	@Test(dependsOnMethods = { "validateLoginPage" })
	public void testValidLogin() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

	}
}

//	//// Anotatin  ////
//	
//	//public class LoginTest extends Base {
//
//	    String baseUrl = ConfigReader.getProperty("baseUrl");
//
//	    @Test(dependsOnMethods = { "validateLoginPage" })
//	    public void testValidLogin() {
//
//	        ExtentTestManager.getTest().log(Status.INFO, "Starting Test: Valid Login");
//	        LoginPage loginPage = new LoginPage(driver);
//
//	        ExtentTestManager.getTest().log(Status.INFO,
//	                "Logging in with Username: " + ConfigReader.getProperty("username"));
//
//	        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
//
//	        // You can validate login using title, URL or some element
//	        String expectedTitle = "Dashboard";
//	        String actualTitle = driver.getTitle();
//
//	        if (actualTitle.contains(expectedTitle)) {
//	            ExtentTestManager.getTest().pass("Login test passed. Dashboard displayed.");
//	        } else {
//	            ExtentTestManager.getTest().fail("Login failed. Expected title: " + expectedTitle 
//	                + ", but found: " + actualTitle);
//	        }
//
//	        Assert.assertTrue(actualTitle.contains(expectedTitle), "Login Validation Failed");
//	    }
//
//	    @Test
//	    public void validateLoginPage() {
//
//	        ExtentTestManager.getTest().log(Status.INFO, "Starting Test: Validate Login Page");
//
//	        LoginPage loginPage = new LoginPage(driver);
//	        loginPage.loginPageValidation();
//
//	        ExtentTestManager.getTest().pass("Login page validation successful.");
//	    }
//	}

//	@Test
//	public void selectTestGeneratorModule() {
//		WebElement testGeneratorModule = driver.findElement(By.xpath("//*[contains(text(),'Test Generator')]"));
//		testGeneratorModule.click();
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		WebElement testGeneratorHeader = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Test Generator')]")));
//
//		Assert.assertTrue(testGeneratorHeader.isDisplayed(), "Test Generator page not displayed");
//		System.out.println("Test Generator page validation successful.");

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
