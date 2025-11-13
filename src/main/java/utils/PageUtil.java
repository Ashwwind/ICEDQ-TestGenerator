package utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtil {

	private static final Logger log = LogManager.getLogger(PageUtil.class);
	public static final int SHOTW = 50; // short wait = 10 seconds
	private static WebDriverWait wait;
	private WebDriver driver;

	public PageUtil(WebDriver driver) {
		this.driver = driver;
		PageUtil.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public static WebDriverWait waitForElements(WebDriver driver, int timeoutInSeconds) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementClickInterceptedException.class);
		return wait;
	}

	// Wait for the element to be visible.

	// Wait for the element to be visible.
	public static boolean isDisplayed(WebDriver driver, By by, int timeout) {
		try {
			isInvisibleLoader(driver,
					By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div"));
			isInvisibleLoader(driver,
					By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div"));
			return waitForElements(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by))
					.isDisplayed();

		} catch (TimeoutException te) {

			return new WebDriverWait(driver, Duration.ofSeconds(timeout + 10))
					.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed(); // fallback technique

		} catch (Exception e) {
			System.out.println("Element is not visiblle");
		}
		return false;

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

	public static void clickOnElement(WebDriver driver, By by, int timeout) {
		try {

			boolean state = isDisplayed(driver, by, timeout);
			if (state) {
				waitForElements(driver, timeout).until(ExpectedConditions.elementToBeClickable(by)).click();
			} else {
				log.info("Element not visible : " + by);
			}
		} catch (Exception e) {
			System.out.println("Element is not clickable");
		}
	}

	//
	public static void isInvisibleLoader(WebDriver driver, By locator) {
		waitForElements(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}


	// Enter data in the filed.
	public static void sendkeysToElement(WebDriver driver, By locator, String label, String input) {
		System.out.println("Entering value on " + label + " input : " + input);
		waitForElements(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(input);
		System.out.println("Entered value on " + label + " input : " + input);
	}

	public static WebDriverWait waitMethod(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SHOTW));

		wait.ignoreAll(java.util.Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class,
				ElementClickInterceptedException.class));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString()
				.equals("complete"));
		return wait;
	}


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
