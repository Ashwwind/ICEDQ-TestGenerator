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
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginPageValidation();	
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