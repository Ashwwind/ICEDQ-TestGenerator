package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
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
		PageUtil.clickOnElement(driver, loginButton, "SingIn button", 10);
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public void loginPageValidation() {
		driver.get(ConfigReader.getProperty("baseUrl"));

		// URL validation
		verifyUrlOfPage();

		// Title validation
		verifyTitleOfPage();

		// Logo validation
		verifyLogoIsDisplayed();

		// Username and Password validation
		verifyUsernameAndPasswordFields();

		// Checkbox validation
		verifyRememberMeCheckbox();

		// Sign In button and Forgot Password link validation
		verifySignInButtonAndForgotPasswordLink();

	}

	// URL validation
	public void verifyUrlOfPage() {
		String expectedUrlPart = "icedq";
		String currentUrl = driver.getCurrentUrl();

		if (currentUrl.contains(expectedUrlPart)) {
			ExtentManager.logPass("Page URL is correct: " + currentUrl);
		} else {
			ExtentManager.logFail(
					"Page URL is incorrect. Expected to contain: " + expectedUrlPart + ", but found: " + currentUrl);
		}
	}

	// URL validation
//	    try {
//	        String currentUrl = driver.getCurrentUrl();
//	        Assert.assertTrue(currentUrl.contains("icedq"), "Base URL does not contain 'icedq'.");
//	       ExtentManager.logInfo("Base URL loaded correctly: " + currentUrl);
//	    } catch (Exception e) {
//	    	ExtentManager.logFail("Base URL validation failed. Error: " + e.getMessage());
//	    }

	// Title validation
	public void verifyTitleOfPage() {
		String expectedTitle = "Sign in to iCEDQ DataOps Platform";
		String actualTitle = driver.getTitle();

		// check point
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("The getting page title is correct.");
			ExtentManager.logInfo("The getting page title is correct: " + actualTitle);
		} else {
			System.out.println("The getting page title is incorrect. Error: " + actualTitle);
			ExtentManager.logFail("The getting page title is incorrect. Actual title: " + actualTitle);
		}
	}

//	    String actualTitle = null;
//		String expectedTitle = null;
//		try {
//			actualTitle = driver.getTitle();
//			expectedTitle = "Sign in to iCEDQ DataOps Platform";
//			 ExtentManager.logInfo("The actual page title. '" + actualTitle + "'");
//		} catch (Exception e) {
//			 ExtentManager.logFail("The actual page title. " + actualTitle + "The expected page title. " + expectedTitle);
//		}
//	   

	// Logo validation
	public void verifyLogoIsDisplayed() {
		WebElement logo = driver.findElement(By.xpath("/html/body/div/div[1]/div"));

		// check point
		if (logo.isDisplayed()) {
			System.out.println("Logo is displayed.");
			ExtentManager.logPass("Logo is displayed.");
		} else {
			System.out.println("Logo is not displayed.");
			ExtentManager.logFail("Logo is not displayed.");
		}
	}

//		try {
//		    WebElement logo = driver.findElement(By.xpath("/html/body/div/div[1]/div"));
//		    Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed.");
//		    ExtentManager.logPass("Logo is displayed. " );
//		} catch (Exception e) {
//		    ExtentManager.logFail("Logo validation failed. Error: " + e.getMessage());
//		}

	// Username and Password validation
	public void verifyUsernameAndPasswordFields() {

		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));

		// check point
		if (usernameField.isDisplayed() && passwordField.isDisplayed()) {
			System.out.println("Username and password fields are displayed.");
			ExtentManager.logPass("Username and password fields are displayed.");
		} else {
			System.out.println("Username and/or password field is not displayed.");
			ExtentManager.logFail("Username and/or password field is not displayed.");
		}
	}

//	try
//	{
//		WebElement usernameField = driver.findElement(By.id("username"));
//		WebElement passwordField = driver.findElement(By.id("password"));
//
//		Assert.assertTrue(usernameField.isDisplayed(), "Username field is not displayed.");
//		Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed.");
//
//		ExtentManager.logPass("Username and password fields are displayed.");
//	}catch(
//	Exception e)
//	{
//		ExtentManager.logFail("Username/Password field validation failed. Error: " + e.getMessage());
//	}

	// Checkbox validation
	public void verifyRememberMeCheckbox() {

		WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));

		// check point
		if (checkbox.isDisplayed()) {
			System.out.println("Remember Me checkbox is displayed.");
			ExtentManager.logPass("Remember Me checkbox is displayed.");
		} else {
			System.out.println("Remember Me checkbox is not displayed.");
			ExtentManager.logFail("Remember Me checkbox is not displayed.");
		}
	}

//	try
//	{
//		WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
//		Assert.assertTrue(checkbox.isDisplayed(), "Remember Me checkbox is not displayed.");
//		ExtentManager.logPass("Remember Me checkbox is displayed.");
//	}catch(
//	Exception e)
//	{
//		ExtentManager.logFail("Checkbox validation failed. Error: " + e.getMessage());
//	}

	// Sign In button and Forgot Password link validation
	public void verifySignInButtonAndForgotPasswordLink() {

		WebElement signInButton = driver.findElement(By.id("kc-login"));
		WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Password?"));

		// check point
		if (signInButton.isDisplayed() && forgotPasswordLink.isDisplayed()) {
			System.out.println("Sign In button and Forgot Password link are displayed.");
			ExtentManager.logPass("Sign In button and Forgot Password link are displayed.");
		} else {
			System.out.println("Sign In button and/or Forgot Password link are not displayed.");
			ExtentManager.logFail("Sign In button and/or Forgot Password link are not displayed.");
		}
	}

//	try
//	{
//		WebElement signInButton = driver.findElement(By.id("kc-login"));
//		WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Password?"));
//
//		Assert.assertTrue(signInButton.isDisplayed(), "Sign In button is not displayed.");
//		Assert.assertTrue(forgotPasswordLink.isDisplayed(), "Forgot Password link is not displayed.");
//
//		ExtentManager.logPass("Sign In button and Forgot Password link are displayed.");
//	}catch(
//	Exception e)
//	{
//		ExtentManager.logFail("Sign In or Forgot Password validation failed. Error: " + e.getMessage());
//	}
}
