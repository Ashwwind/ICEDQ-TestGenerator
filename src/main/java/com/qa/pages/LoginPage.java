package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.utils.PageUtil;

public class LoginPage extends PageUtil {
	
	private WebDriver driver;
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	By usernameField = PageUtil.getElementLocator(prop.getProperty("login.username"));
	By passwordField = PageUtil.getElementLocator(prop.getProperty("login.password"));
	By loginButton = PageUtil.getElementLocator(prop.getProperty("login.submit"));

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		PageUtil.sendkeysToElement(driver, usernameField, "Username", username);
	}

	public void enterPassword(String password) {

		PageUtil.sendkeysToElement(driver, passwordField, "password", password);
	}

	public void clickLogin() {
		PageUtil.clickOnElement(driver, loginButton, 10);
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
	public void loginPageValidation() {
	    driver.get(ConfigReader.getProperty("baseUrl"));

	 // URL validation
	    try {
	        String currentUrl = driver.getCurrentUrl();
	        Assert.assertTrue(currentUrl.contains("icedq"), "Base URL does not contain 'icedq'.");
	        ExtentListener.test.get().log(Status.PASS, "Base URL loaded correctly: " + currentUrl);
	    } catch (Exception e) {
	        ExtentListener.test.get().log(Status.FAIL, "Base URL validation failed. Error: " + e.getMessage());
	    }

	    // Title validation
	    String actualTitle = null;
		String expectedTitle = null;
		try {
			actualTitle = driver.getTitle();
			expectedTitle = "Sign in to iCEDQ DataOps Platform";
			ExtentListener.test.get().log(Status.PASS, "The actual page title." + actualTitle);
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "The actual page title. " + actualTitle + "The expected page title. " + expectedTitle);
		}
	   

		// Logo validation
		try {
		    WebElement logo = driver.findElement(By.xpath("/html/body/div/div[1]/div"));
		    Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed.");
		    ExtentListener.test.get().log(Status.PASS, "Logo is displayed.");
		} catch (Exception e) {
		    ExtentListener.test.get().log(Status.FAIL, "Logo validation failed. Error: " + e.getMessage());
		}
		
		
		// Username and Password validation
		try {
		    WebElement usernameField = driver.findElement(By.id("username"));
		    WebElement passwordField = driver.findElement(By.id("password"));

		    Assert.assertTrue(usernameField.isDisplayed(), "Username field is not displayed.");
		    Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed.");

		    ExtentListener.test.get().log(Status.PASS, "Username and password fields are displayed.");
		} catch (Exception e) {
		    ExtentListener.test.get().log(Status.FAIL, "Username/Password field validation failed. Error: " + e.getMessage());
		}

		// Checkbox validation
		try {
		    WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
		    Assert.assertTrue(checkbox.isDisplayed(), "Remember Me checkbox is not displayed.");
		    ExtentListener.test.get().log(Status.PASS, "Remember Me checkbox is displayed.");
		} catch (Exception e) {
		    ExtentListener.test.get().log(Status.FAIL, "Checkbox validation failed. Error: " + e.getMessage());
		}

		// Sign In button and Forgot Password link validation
		try {
		    WebElement signInButton = driver.findElement(By.id("kc-login"));
		    WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Password?"));

		    Assert.assertTrue(signInButton.isDisplayed(), "Sign In button is not displayed.");
		    Assert.assertTrue(forgotPasswordLink.isDisplayed(), "Forgot Password link is not displayed.");

		    ExtentListener.test.get().log(Status.PASS, "Sign In button and Forgot Password link are displayed.");
		} catch (Exception e) {
		    ExtentListener.test.get().log(Status.FAIL, "Sign In or Forgot Password validation failed. Error: " + e.getMessage());
		}

	}
	
	

	
}