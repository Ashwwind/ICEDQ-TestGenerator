package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import base.Base;
import utils.PageUtil;
import config.ConfigReader;

public class LoginPage extends Base{
	private WebDriver driver;

	// Vipul
	
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
			PageUtil.waitForElement(driver, 10);
			PageUtil.clickOnElement(driver, loginButton, "Sign In");
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
	
	public void loginPageValidation()
	{
		driver.get(ConfigReader.getProperty("baseUrl"));

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
	
	

	
	
}
