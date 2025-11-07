package utils;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtil {

	private static final Logger log = LogManager.getLogger(PageUtil.class);
	public static final int SHOTW = 10; // short wait = 10 seconds
	private WebDriver driver;
	private static WebDriverWait wait;

	public PageUtil(WebDriver driver) {
		this.driver = driver;
		PageUtil.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Wait for the element to be clickable.
	public static void waitForTheElementToBeClickable(WebDriver driver, By by, String label) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			System.out.println("Clicking on " + label);
			driver.findElement(by).click();
			System.out.println("Clicked on " + label);
		} catch (Exception e) {
			System.out.println("Element is not clickable");
		}
	}

	// Click method.
	public static void clickOnElement(WebDriver driver, By locator, String label) {
		System.out.println("Clicking on " + label);
		driver.findElement(locator).click();
		System.out.println("Clicked on " + label);
	}

	// Enter data in the filed.
	public static void sendkeysToElement(WebDriver driver, By locator, String label, String input) {
		System.out.println("Entering value on " + label + " input : " + input);
		driver.findElement(locator).sendKeys(input);
		System.out.println("Entered value on " + label + " input : " + input);
	}

	public static WebDriverWait waitForElement(WebDriver driver, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout));
	}

	public static void waitForPageLoad(WebDriver driver, int timeOutInSec) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
			wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
					.equals("complete"));
			System.out.println("Page loaded successfully.");
		} catch (Exception e) {
			System.out.println("Page did not load within " + timeOutInSec + " seconds.");
		}
	}

	public static WebDriverWait waitMethod(WebDriver driver) {

		// Create wait with Selenium 4 syntax
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SHOTW));

		// Ignore common transient exceptions
		wait.ignoreAll(java.util.Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class,
				ElementClickInterceptedException.class));

		// Wait for the page to fully load
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString()
				.equals("complete"));

		return wait;
	}

	// vipul

	/**
	 * Returns a Selenium By locator object from a formatted string. Supported
	 * format: "type~~value" (e.g., "xpath~~//div[@id='main']")
	 * 
	 * Supported locator types: xpath, id, css, name, classname, linktext,
	 * partiallinktext, tagname
	 */
	public static By getElementLocator(String locator) {
		String[] parts = locator.split("~~", 2);
		String type = parts[0].trim();
		String value = parts[1].trim();

		switch (type.toLowerCase()) {
		case "id":
			return By.id(value);
		case "name":
			return By.name(value);
		case "xpath":
			return By.xpath(value);
		case "css":
			return By.cssSelector(value);
		case "linktext":
			return By.linkText(value);
		default:
			throw new IllegalArgumentException("Unknown locator type: " + type);
		}
	}
}
