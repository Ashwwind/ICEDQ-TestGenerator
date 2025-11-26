package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {

	// 🔹 Locators
	By advancedButtonLocator = By.xpath("//button[text()='Advanced']");
	By qaDndOptionLocator = By.xpath("//option[text()='QA_DND']");
	By continueLinkLocator = By.xpath("//a[text()='Continue to 192.168.100.35 (unsafe)']");
	By signInButtonLocator = By.xpath("//input[@type='submit']");
	By connectorsLinkLocator = By.xpath("//a[text()='Connectors']");
	By refreshButtonLocator = By.xpath("//button[text()='Refresh']");
	By workspaceDropdownLocator = By.id("workspace-dropdown");
	By secretNameFieldLocator = By.name("secretName");
	By passwordFieldLocator = By.name("password");
	By dashboardHeaderLocator = By.xpath("//h1[text()='Dashboard']");
	By usernameFieldLocator = By.id("username");
	By loginHeaderLocator = By.xpath("//h1[text()='Login']");
	By microsoftDataverseLinkLocator = By.xpath("//a[text()='Microsoft Dataverse']");
	By loader = By.cssSelector(".loader");

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		
		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(options);
		driver.get("https://qa.onprem.icedq.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void testLoginAndNavigate() {
		/*
		 * waitForElement(advancedButtonLocator).click();
		 * waitForElement(continueLinkLocator).click();
		 * waitForElement(loginHeaderLocator);
		 */

		WebElement usernameField = waitForElement(usernameFieldLocator);
		usernameField.sendKeys("ashwin.d");

		WebElement passwordField = waitForElement(passwordFieldLocator);
		passwordField.sendKeys("Admin@1234");

		waitForElementClickable(By.xpath("//input[@type='submit']")).click();

		//waitForElement(signInButtonLocator).click();
		Assert.assertTrue(driver.getTitle().contains("Dashboard"));

//		waitForElement(dataTestingLinkLocator).click();
//		waitForElement(workspaceSelectLocator).sendKeys("QA_DND");
//		waitForElement(createReconButtonLocator).click();

		// Additional steps would go here...

//		waitForElement(publishButtonLocator).click();
//		waitForElement(runButtonLocator).click();
//		waitForElementToDisappear(loaderLocator);
//		waitForElement(instanceIdLinkLocator).click();
//		waitForElement(highlightIconLocator).click();
	}

	private WebElement waitForElement(By locator) {
	    return new WebDriverWait(driver, Duration.ofSeconds(50))
	            .until(ExpectedConditions.refreshed(
	                    ExpectedConditions.visibilityOfElementLocated(locator)
	            ));
	}


	/*
	 * @AfterClass public void tearDown() { if (driver != null) { driver.quit(); }
	 */

// 🔹 Generic explicit waits
	private WebElement waitForElementVisible(By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	private WebElement waitForElementClickable(By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private WebElement waitForElementPresent(By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	private boolean waitForElementToDisappear(By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	private boolean waitForTitle(String title) {
		return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.titleContains(title));
	}

}
