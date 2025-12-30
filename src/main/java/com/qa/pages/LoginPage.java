package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.utils.PageUtil;

public class LoginPage extends PageUtil {

	private WebDriver driver;

	By usernameField = PageUtil.getElementLocator(prop.getProperty("login.username"));
	By passwordField = PageUtil.getElementLocator(prop.getProperty("login.password"));
	By loginButton = PageUtil.getElementLocator(prop.getProperty("login.submit"));

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		clickOnElement(driver, usernameField, "username or email field.", 10);
		sendkeysToElement(driver, usernameField, "Username", username);
	}

	public void enterPassword(String password) {
		clickOnElement(driver, passwordField, "password field.", 10);
		sendkeysToElement(driver, passwordField, "password", password);
	}

	public void clickLogin() {
		clickOnElement(driver, loginButton, "SingIn button.", 10);
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

}
