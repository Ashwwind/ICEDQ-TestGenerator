package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;

public class PageUtil extends Base {

	private static final Logger log = LogManager.getLogger(PageUtil.class);
	public static final int SHOTW = 6;
	private static WebDriverWait wait;
	public static Properties prop;

	// Locator file loading
	public static void locatotFind() {

		if (prop == null) {
			prop = new Properties();
		}

		String locatorDirPath = System.getProperty("user.dir") + "/src/main/resources/locator/";
		File folder = new File(locatorDirPath);

		if (!folder.exists() || !folder.isDirectory()) {
			log.error("Locator directory not found: {}", locatorDirPath);
		}

		File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".properties"));
		if (listOfFiles == null || listOfFiles.length == 0) {
			log.warn("No locator property files found in {}", locatorDirPath);
			return;
		}

		log.info("Loading locator files from directory: {}", locatorDirPath);

		for (File file : listOfFiles) {
			log.info("Loading locator file: {}", file.getName());

			try (FileInputStream fis = new FileInputStream(file)) {
				Properties tempProps = new Properties();
				tempProps.load(fis);
				prop.putAll(tempProps);
				log.info("Loaded {} locators from {}", tempProps.size(), file.getName());
			} catch (IOException e) {
				log.error("Failed to load locator file '{}': {}", file.getName(), e.getMessage());
			}
		}

		log.info("Total locators loaded: {}", prop.size());
	}

	public static WebDriverWait waitForElements(WebDriver driver, int timeoutInSeconds) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementClickInterceptedException.class);
		return wait;
	}

	// Wait for the element to be visible.
	public static boolean isDisplayed(WebDriver driver, By by, int timeout) {
		try {
			// Wait for any loader to disappear (only once)
			isInvisibleLoader(driver,
					By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div"));

			// Wait for element to be visible
			return waitForElements(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by))
					.isDisplayed();

		} catch (TimeoutException te) {
			// Fallback: extra wait
			try {
				return new WebDriverWait(driver, Duration.ofSeconds(timeout + 50))
						.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
			} catch (Exception e) {
				ExtentManager.logInfo("Element not visible after extended wait: " + by);
				return false;
			}

		} catch (Exception e) {
			ExtentManager.logInfo("Error while checking visibility of element: " + by + " ➝ " + e.getMessage());
			return false;
		}
	}

	// Click on the web element
	public static void clickOnElement(WebDriver driver, By by, String label, int timeout) {
		try {
			if (!isDisplayed(driver, by, timeout)) {
				ExtentManager.logInfo(label + " not displayed, retrying click");
				//throw new RuntimeException("Element not visible");
			}

			validateElementIsVisible(driver, by, label);

			ExtentManager.logInfo("Clicking on " + label);

			waitForElements(driver, timeout).until(ExpectedConditions.elementToBeClickable(by)).click();
			
			waitForSeconds(1);

			ExtentManager.logPass("Clicked on " + label);

		} catch (Exception e) {
			ExtentManager.logFail("Failed to click on " + label + " ➝ " + e.getMessage());
			throw e; // 🔥 VERY IMPORTANT — let executeStep handle screenshot
		}
	}


	public static boolean validateElementIsVisible(WebDriver driver, By locator, String label) {
		boolean isVisible = isDisplayed(driver, locator, SHOTW);

		if (isVisible) {
			ExtentManager.logInfo(label + " is visible.");
			waitForSeconds(1);
		} else {
			ExtentManager.logFail(label + " is NOT visible.");
		}
		return isVisible;
	}

	//
	public static void isInvisibleLoader(WebDriver driver, By locator) {
		waitForElements(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	
	// Wait for just seconds
	public static void waitForSeconds(long seconds) {
	    try {
	        Thread.sleep(seconds * 50);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}

	// Enter data in the filed.
	public static void sendkeysToElement(WebDriver driver, By locator, String label, String input) {
		try {
			validateElementIsVisible(driver, locator, label);

			ExtentManager.logInfo("Entering value on " + label + " input: " + input);

			waitForElements(driver, SHOTW).until(ExpectedConditions.visibilityOfElementLocated(locator))
					.sendKeys(input);
			
			 waitForSeconds(3); // wait after entering text.

			ExtentManager.logPass("Entered value on " + label);

		} catch (Exception e) {
			ExtentManager.logFail("Failed to enter value on " + label + " ➝ " + e.getMessage());
			throw e; // 🔥 MUST rethrow so executeStep can capture screenshot & fail
		}
	}

	// Enter data in the filed.
	public static void sendkeysToEnter(WebDriver driver, By locator, String label) {
		try {
			validateElementIsVisible(driver, locator, label);
			
			ExtentManager.logInfo("Pressing ENTER on " + label);

			waitForElements(driver, SHOTW).until(ExpectedConditions.visibilityOfElementLocated(locator))
					.sendKeys(Keys.ENTER);
			
			 waitForSeconds(2); // wait after ENTER.

			ExtentManager.logPass("Pressed ENTER on " + label);

		} catch (Exception e) {
			ExtentManager.logFail("Failed to press ENTER on " + label + " ➝ " + e.getMessage());
			throw e; // 🔥 Rethrow so executeStep handles screenshot & toast
		}
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

	// Navigate to any URL
	public void goTo(String url) {
		driver.navigate().to(url);
	}

	// Back
	public void back() {
		driver.navigate().back();
	}

	// Forward
	public void forward() {
		driver.navigate().forward();
	}

	// Refresh
	public void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * Upload a file stored in the project folder.
	 *
	 * @param driver       WebDriver instance
	 * @param fileInputLoc Locator for <input type="file"> element
	 * @param relativePath Relative path inside project (example:
	 *                     "/src/test/resources/testdata.xlsx")
	 */
	public static void uploadFile(WebDriver driver, By fileInputLocator, String fileName) {
		try {
			// Build full path
			String fullPath = System.getProperty("user.dir") + "/src/test/resources/ImportTemplate/" + fileName;

			// Validate file existence
			Path path = Paths.get(fullPath);
			if (!Files.exists(path)) {
				throw new RuntimeException("❌ File does not exist: " + fullPath);
			}

			// Find hidden input
			WebElement fileInput = driver.findElement(fileInputLocator);

			// Upload the file
			fileInput.sendKeys(fullPath);

			System.out.println("✅ Uploaded file: " + fullPath);
		} catch (Exception e) {
			throw new RuntimeException("File upload failed: " + e.getMessage());

		}
	}

	// Get parent window handle
	public static String getParentWindow(WebDriver driver) {
		log.info("Capturing parent window handle");
		return driver.getWindowHandle();
	}

	// Wait for new window to open and switch to it
	public static String switchToNewWindow(WebDriver driver, String parentWindow, int SHOTW) {
		log.info("Waiting for new window to open...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SHOTW));

		// Wait until window count > 1
		wait.until(driverObj -> driverObj.getWindowHandles().size() > 1);

		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				log.info("Switching to child window: " + window);
				driver.switchTo().window(window);
				return window; // return child window handle
			}
		}
		throw new RuntimeException("Child window not found!");
	}

	// Switch back to parent window
	public static void switchBackToParent(WebDriver driver, String parentWindow) {
		log.info("Switching back to parent window: " + parentWindow);
		driver.switchTo().window(parentWindow);
	}

	// Close child & return to parent
	public static void closeChildAndReturn(WebDriver driver, String childWindow, String parentWindow) {
		log.info("Closing child window and switching back to parent.");

		try {
			// First, switch to child
			driver.switchTo().window(childWindow);
		} catch (NoSuchWindowException e) {
			log.warn("Child window already closed. Skipping close.");
		}

		try {
			driver.close(); // safely close child
		} catch (Exception e) {
			log.warn("Error closing child window: " + e.getMessage());
		}

		// Now switch back to parent
		for (String win : driver.getWindowHandles()) {
			if (win.equals(parentWindow)) {
				driver.switchTo().window(parentWindow);
				return;
			}
		}

		throw new RuntimeException("Parent window not found after closing child!");
	}

	// Click the Checksum rule type from the wizard page.
	public boolean clickOnField(WebDriver driver, By locator, String fieldName, String fieldType) {
		boolean flag = true;
		try {
			isInvisibleLoader(driver, getElementLocator(prop.getProperty("loderIsDisplayed")));

			if (validateElementIsVisible(driver, locator, fieldName + " " + fieldType)) {

				clickOnElement(driver, locator, fieldName + " " + fieldType, 50);

				ExtentManager.logInfo(fieldName + " " + fieldType + " is validated...");
			} else {
				flag = false;
			}
		} catch (Exception e) {
			ExtentManager.logError("error occured while :" + fieldName + " " + fieldType);
			ExtentManager.logFail("error occured while :" + fieldName + " " + fieldType);
		}
		return flag;

	}

	public boolean sendkeysToElement1(WebDriver driver, By locator, String fieldName, String input) {
		boolean flag = true;
		try {
			// Wait for loader to disappear
			isInvisibleLoader(driver, getElementLocator(prop.getProperty("loderIsDisplayed")));

			// Validate element visibility
			if (validateElementIsVisible(driver, locator, fieldName)) {

				// Click before sending keys
				clickOnElement(driver, locator, fieldName, 20);

				// Send keys
				waitForElements(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(locator))
						.sendKeys(input);
				
				waitForSeconds(3); // wait to ENTER

				ExtentManager.logInfo("Entered value '" + input + "' on " + fieldName);
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
			ExtentManager.logError("Error occurred while entering value on " + fieldName + " : " + e.getMessage());
			ExtentManager.logFail("Error occurred while entering value on " + fieldName + " : " + e.getMessage());
		}
		return flag;
	}
	
	public boolean sendKeysEnterToElement2(WebDriver driver, By locator, String fieldName) {
		boolean flag = true;
		try {
			// Wait for loader to disappear
			isInvisibleLoader(driver, getElementLocator(prop.getProperty("loderIsDisplayed")));

			// Validate element visibility
			if (validateElementIsVisible(driver, locator, fieldName)) {

				// Click before pressing ENTER
				clickOnElement(driver, locator, fieldName, 20);

				// Press ENTER
				waitForElements(driver, SHOTW).until(ExpectedConditions.visibilityOfElementLocated(locator))
						.sendKeys(Keys.ENTER);

				waitForSeconds(2); // wait after ENTER

				ExtentManager.logInfo("Pressed ENTER on " + fieldName);
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
			ExtentManager.logError("Error occurred while pressing ENTER on " + fieldName + " : " + e.getMessage());
			ExtentManager.logFail("Error occurred while pressing ENTER on " + fieldName + " : " + e.getMessage());
		}
		return flag;
	}


	// Error Capture Utility
	private static final By TOAST_MESSAGE = By.xpath("//div[contains(@class,'e-toast-content')]");

	// Check if any error toast is present
	public static boolean isErrorToastPresent(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			List<WebElement> toasts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TOAST_MESSAGE));
			return !toasts.isEmpty();
		} catch (TimeoutException e) {
			return false;
		}
	}

	// Capture UI errors if present
	public static String captureUIErrorIfPresent(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

			List<WebElement> toasts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TOAST_MESSAGE));

			StringBuilder errors = new StringBuilder();

			for (WebElement toast : toasts) {
				String msg = toast.getText().trim();
				if (!msg.isEmpty()) {
					errors.append(msg).append(" | ");
					ExtentManager.logFail("UI Toast Error: " + msg);
				}
			}

			// 📸 Screenshot ONLY if toast exists
			ExtentManager.captureScreenshot("UI_Toast_Error");

			return errors.toString();

		} catch (TimeoutException e) {
			return null; // No toast appeared
		}
	}

	// Scrolls the page
	public void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// UI validation
	public void validateElement(WebDriver driver, By locator, String lable, String type) {

		validateElementIsVisible(driver, locator, type);

	}

	// 
	public boolean isElementDisplayed(WebDriver driver, By locator, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			wait.pollingEvery(Duration.ofSeconds(40)); // check every 1 second
			wait.ignoring(NoSuchElementException.class);

			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;

		} catch (TimeoutException e) {
			return false;
		}
	}


}