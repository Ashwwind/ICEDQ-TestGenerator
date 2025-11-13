package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.PageUtil;
import config.ConfigReader;

public class LoginPage {
	WebDriver driver;

	private By usernameField;
	private By passwordField;
	private By loginButton;
	private By loader;
	private By testGeneratorModule;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		usernameField = PageUtil.getElementLocator(ConfigReader.getProperty("login.username"));
		passwordField = PageUtil.getElementLocator(ConfigReader.getProperty("login.password"));
		loginButton = PageUtil.getElementLocator(ConfigReader.getProperty("login.submit"));
		loader = PageUtil.getElementLocator(ConfigReader.getProperty("loderIsDisplayed"));
		testGeneratorModule = PageUtil.getElementLocator(ConfigReader.getProperty("home.testGenerator"));
	}

//	By usernameField = PageUtil.getElementLocator(prop.getProperty("login.username"));
//	By passwordField = PageUtil.getElementLocator(prop.getProperty("login.password"));
//	By loginButton = PageUtil.getElementLocator(prop.getProperty("login.submit"));
//	By loader = PageUtil.getElementLocator(prop.getProperty("loderIsDisplayed"));
//	By testGeneratorModule = PageUtil.getElementLocator(prop.getProperty("home.testGenerator"));

	public void enterUsername(String username) {
		PageUtil.sendkeysToElement(driver, usernameField, "Username", username);
	}

	public void enterPassword(String password) {
		PageUtil.sendkeysToElement(driver, passwordField, "password", password);
	}

	public void clickLogin() {
<<<<<<< HEAD
		PageUtil.waitForPageToLoad(driver, 20);
			PageUtil.waitForElement(driver, 10);
			PageUtil.clickOnElement(driver, loginButton, "Sign In");
=======
		PageUtil.isDisplayed(driver, usernameField, 0);
		PageUtil.isDisplayed(driver, passwordField, 0);
		PageUtil.clickOnElement(driver, loginButton, "Sign In");
		PageUtil.isInvisibleLoader(driver, loader);
>>>>>>> 36350c3 (Code change done from Office at 11-11-25)
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public void loginPageValidation() {
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

	public void clickTestGeneratorModuleFromHome() {
		PageUtil.clickOnElement(driver, testGeneratorModule, 10);
	}

}
