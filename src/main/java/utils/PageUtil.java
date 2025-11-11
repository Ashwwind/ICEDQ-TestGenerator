package utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public static void waitForTheElementToBeVisible(WebDriver driver, By by, String label) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			System.out.println("Element is visible: " + label);
		} catch (Exception e) {
			System.out.println("Element is not visiblle");
		}
	}

	// Wait for the element to be visible.
	public static boolean isDisplayed(WebDriver driver, By by, int timeout) {
		try {
			isInvisibleLoader(driver, By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div"));
			isInvisibleLoader(driver, By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div"));
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

	// Click method.
	public static void clickOnElement(WebDriver driver, By locator, String label) {
		System.out.println("Clicking on " + label);
		clickOnElement(driver, locator, 10);
		System.out.println("Clicked on " + label);
	}

	// Ashwin
	public static void clickElementSafely(WebDriver driver, By locator, String elementName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {
			// Wait for page load
			waitForPageToLoad(driver, 10);

			// Wait for element to be present and visible
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

			// Wait until clickable
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));

			// Scroll into view (sometimes needed if element is off-screen)
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

			try {
				element.click();
				System.out.println("✅ Clicked on element: " + elementName);
			} catch (ElementClickInterceptedException e) {
				System.out.println("⚠️ Element click intercepted for: " + elementName + ". Retrying with JS click...");
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			}

		} catch (TimeoutException e) {
			System.out.println("❌ Timeout waiting for element to be clickable: " + elementName);
			throw e;
		} catch (NoSuchElementException e) {
			System.out.println("❌ Element not found: " + elementName);
			throw e;
		} catch (Exception e) {
			System.out.println("❌ Unexpected error clicking element: " + elementName);
			e.printStackTrace();
			throw e;
		}
	}

	// Enter data in the filed.
	public static void sendkeysToElement(WebDriver driver, By locator, String label, String input) {
		System.out.println("Entering value on " + label + " input : " + input);
		waitForElements(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(input);
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

	// Waits until the page is completely loaded with a custom timeout

	public static void waitForPageToLoad(WebDriver driver, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
						.executeScript("return document.readyState").equals("complete"));
	}

	public static void waitForTheElementToBeClickable1(WebDriver driver, By locator, String elementName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		System.out.println(elementName + " is clickable.");
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
