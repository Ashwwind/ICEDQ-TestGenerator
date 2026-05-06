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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;

public class PageUtil extends Base {

	private static final Logger log = LogManager.getLogger(PageUtil.class);
	public static final int SHOTW = 20;
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

	// Wait until an element is clickable
	public WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutInSeconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Wait until an element is visible
	public WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Wait for loader to disappear
	public void waitForLoaderToDisappear(WebDriver driver, By loaderLocator, int timeoutInSeconds) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
				.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
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
				return new WebDriverWait(driver, Duration.ofSeconds(timeout + 20))
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
	public static boolean clickOnElement(WebDriver driver, By by, String label, int timeout) {
		try {
			if (!isDisplayed(driver, by, timeout)) {
				ExtentManager.logInfo(label + " not displayed, retrying click");
				return false;
			}

			ExtentManager.logInfo("Clicking on " + label);

			WebElement element = waitForElements(driver, timeout).until(ExpectedConditions.elementToBeClickable(by));

			try {
				// Primary click
				element.click();
			} catch (Exception e1) {
				ExtentManager.logInfo("Normal click failed, trying scroll + click");

				try {
					// Scroll into view and retry
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

					element.click();
				} catch (Exception e2) {
					ExtentManager.logInfo("Scroll click failed, trying JS click");

					// Final fallback: JS click
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				}
			}

			ExtentManager.logPass("Clicked on " + label);
			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to click on " + label + " ➝ " + e.getMessage());
			return false;
		}
	}

	public static boolean validateElementIsVisible(WebDriver driver, By locator, String label) {
		try {
			boolean isVisible = isDisplayed(driver, locator, SHOTW);

			if (isVisible) {
				ExtentManager.logPass(label + " is visible.");
			} else {
				ExtentManager.logFail(label + " is NOT visible. Locator: " + locator);
			}

			return isVisible;

		} catch (Exception e) {
			ExtentManager.logFail("Exception while validating visibility of " + label + " ➝ " + e.getMessage());
			return false;
		}
	}

	//
	public static void isInvisibleLoader(WebDriver driver, By locator) {
		waitForElements(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Wait for just seconds
	public static void waitForSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 20);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void sendkeysToElement(WebDriver driver, By locator, String label, String input) {
		try {
			boolean isVisible = validateElementIsVisible(driver, locator, label);

			if (!isVisible) {
				throw new RuntimeException(label + " is not visible. Cannot enter value.");
			}

			ExtentManager.logInfo("Entering value on " + label + " input: " + input);

			WebElement element = waitForElements(driver, SHOTW)
					.until(ExpectedConditions.visibilityOfElementLocated(locator));

			try {
				// Clear existing value
				element.clear();

				// Normal sendKeys
				element.sendKeys(input);

			} catch (Exception e1) {
				ExtentManager.logInfo("Normal sendKeys failed, trying JS fallback");

				// Fallback: JS set value
				((JavascriptExecutor) driver).executeScript("arguments[0].value='" + input + "';", element);
			}

			ExtentManager.logPass("Entered value on " + label);

		} catch (Exception e) {
			ExtentManager.logFail("Failed to enter value on " + label + " ➝ " + e.getMessage());
			throw e; // keep this ✅
		}
	}

	public static void sendkeysToEnter(WebDriver driver, By locator, String label) {
		try {
			boolean isVisible = validateElementIsVisible(driver, locator, label);

			if (!isVisible) {
				throw new RuntimeException(label + " is not visible. Cannot press ENTER.");
			}

			ExtentManager.logInfo("Pressing ENTER on " + label);

			WebElement element = waitForElements(driver, SHOTW).until(ExpectedConditions.elementToBeClickable(locator));

			try {
				// Ensure focus before pressing ENTER
				element.click();
				element.sendKeys(Keys.ENTER);

			} catch (Exception e1) {
				ExtentManager.logInfo("Normal ENTER failed, trying Actions fallback");

				try {
					// Fallback: Actions class
					new Actions(driver).moveToElement(element).click().sendKeys(Keys.ENTER).perform();

				} catch (Exception e2) {
					ExtentManager.logInfo("Actions ENTER failed, trying JS fallback");

					// Final fallback: JS trigger
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].dispatchEvent(new KeyboardEvent('keydown', {'key':'Enter'}));", element);
				}
			}

			ExtentManager.logPass("Pressed ENTER on " + label);

		} catch (Exception e) {
			ExtentManager.logFail("Failed to press ENTER on " + label + " ➝ " + e.getMessage());
			throw e; // keep this ✅
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

	///// This methods are used in the Template tab /////
	//
	// Template tab
	//
	//

	public static boolean validateField(WebDriver driver, By locator, String fieldName, int timeout) {
		try {
			WebDriverWait wait = waitForElements(driver, timeout);

			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

			if (element.isDisplayed()) {
				ExtentManager.logInfo(fieldName + " is visible and validated");
				return true;
			} else {
				ExtentManager.logInfo(fieldName + " is not visible");
				return false;
			}

		} catch (TimeoutException e) {
			ExtentManager.logFail(fieldName + " not visible within " + timeout + " seconds");
			return false;

		} catch (Exception e) {
			ExtentManager.logFail("Error validating " + fieldName + " ➝ " + e.getMessage());
			return false;
		}
	}

	public boolean clickOnField(WebDriver driver, By locator, String fieldName, String fieldType,
			boolean validateBeforeClick) {

		String label = fieldName + " " + fieldType;

		try {

			// 🔹 Click action
			boolean isClicked = clickOnElement(driver, locator, label, 50);

			if (!isClicked) {
				ExtentManager.logFail("Failed to click " + label);
				return false;
			}

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Error while clicking " + label + " ➝ " + e.getMessage());
			return false;
		}
	}

	public boolean sendkeysToElement1(WebDriver driver, By locator, String fieldName, String input) {
		try {
			// 1️⃣ Wait for loader to disappear
			isInvisibleLoader(driver, getElementLocator(prop.getProperty("loderIsDisplayed")));

			// 2️⃣ Ensure element is clickable/visible
			WebElement element = waitForElements(driver, 50).until(ExpectedConditions.elementToBeClickable(locator));

			// 3️⃣ Click before typing
			try {
				element.click();
			} catch (Exception e1) {
				ExtentManager.logInfo("Click failed, trying JS click on " + fieldName);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			}

			// 4️⃣ Send keys normally
			try {
				element.clear(); // Clear existing value
				element.sendKeys(input);

			} catch (Exception e2) {
				ExtentManager.logInfo("Normal sendKeys failed, trying JS fallback for " + fieldName);
				((JavascriptExecutor) driver).executeScript("arguments[0].value='" + input + "';", element);
			}

			// 5️⃣ Optional: verify value is set (avoids fixed sleep)
			// waitForElements(driver, 5).until(d ->
			// element.getAttribute("value").equals(input));

			ExtentManager.logPass("Entered value '" + input + "' on " + fieldName);
			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Exception while entering value on " + fieldName + " ➝ " + e.getMessage());
			return false;
		}
	}

	public boolean sendKeysEnterToElement2(WebDriver driver, By locator, String fieldName) {
		try {
			// 1️⃣ Wait for loader to disappear
			isInvisibleLoader(driver, getElementLocator(prop.getProperty("loderIsDisplayed")));

			// 2️⃣ Wait for element to be clickable
			WebElement element = waitForElements(driver, SHOTW).until(ExpectedConditions.elementToBeClickable(locator));

			// 3️⃣ Click before pressing ENTER (with fallback)
			try {
				element.click();
			} catch (Exception e1) {
				ExtentManager.logInfo("Click failed, trying JS click on " + fieldName);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			}

			// 4️⃣ Press ENTER with fallback
			try {
				element.sendKeys(Keys.ENTER);
			} catch (Exception e2) {
				ExtentManager.logInfo("Normal ENTER failed, trying Actions fallback on " + fieldName);
				new Actions(driver).moveToElement(element).click().sendKeys(Keys.ENTER).perform();
			}

			// 5️⃣ Optional: verify that ENTER had effect (replace fixed wait)
			// waitForSeconds(1); // minimal wait; replace with actual verification if
			// possible

			ExtentManager.logPass("Pressed ENTER on " + fieldName);
			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Exception while pressing ENTER on " + fieldName + " ➝ " + e.getMessage());
			return false;
		}
	}
	//
	// Template tab
	//
	//

	// Error Capture Utility
	private static final By TOAST_MESSAGE = By.xpath(
			"//div[contains(@class,'e-toast') and (contains(@class,'e-toast-error') or contains(@class,'e-toast-danger'))]");

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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

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