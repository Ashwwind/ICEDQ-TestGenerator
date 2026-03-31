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
import com.qa.pages.TestGeneratorPage.FlowAbortException;


public class PageUtil extends Base {

	private static final Logger log = LogManager.getLogger(PageUtil.class);
	public static final int SHOTW = 3;
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

			waitForElements(driver, timeout).until(ExpectedConditions.elementToBeClickable(by)).click();


			waitForSeconds(2);


			ExtentManager.logPass("Clicked on " + label);

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to click on " + label + " ➝ " + e.getMessage());
			return false;
		}
	}

	public static boolean validateElementIsVisible(WebDriver driver, By locator, String label) {
		boolean isVisible = isDisplayed(driver, locator, SHOTW);

		if (isVisible) {
			ExtentManager.logInfo(label + " is visible.");
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
	        Thread.sleep(seconds * 20);
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
	public static void goTo(String url) {
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

	// =========================================================================
    // clickOnField
    // =========================================================================

    /**
     * Waits for the loader to disappear, validates element visibility, clicks the
     * element, then waits for the loader again after the click.
     *
     * @param driver    active WebDriver instance
     * @param locator   By locator of the target element
     * @param fieldName human-readable field name (for logging)
     * @param fieldType human-readable field type, e.g. "Button", "Tab" (for logging)
     * @return true if the element was successfully clicked, false otherwise
     */
    public boolean clickOnField(WebDriver driver, By locator, String fieldName, String fieldType) {
        String label = fieldName + " " + fieldType;
        try {
            // FIX: Both the pre-click and post-click isInvisibleLoader calls were
            // commented out with __double-underscore__ markers — clearly disabled
            // during debugging and never restored. Without these waits, every click
            // in the entire framework runs without checking whether the page is ready,
            // which is a primary cause of flaky tests. Both are restored.
            isInvisibleLoader(driver, locator);

            if (!validateElementIsVisible(driver, locator, label)) {
                ExtentManager.logError(label + " is not visible.");
                return false;
            }

            boolean isClicked = clickOnElement(driver, locator, label, SHOTW);
            if (!isClicked) {
                ExtentManager.logError("Failed to click " + label);
                return false;
            }

            // Post-click loader wait — ensures the UI has fully settled before the
            // next action begins.
            isInvisibleLoader(driver, locator);

            return true;

        } catch (Exception e) {
            ExtentManager.logError("Error while clicking " + label + ": " + e.getMessage());
            return false;
        }
    }

	
	///////////////////////////////////////////////////////////////////////////////////////
	
	public boolean clickOnField1(WebDriver driver, By locator, String fieldName, String fieldType, boolean expected) {
		String label = fieldName + " " + fieldType;
		try {

			// Check the loader
			isInvisibleLoader(driver, getElementLocator(prop.getProperty("loderIsDisplayed")));

			// Wait for the element to be visible
			if (!validateElementIsVisible(driver, locator, label) || expected) {
				ExtentManager.logError(label + " is not visible on the page.");
				return false;
			}

			clickOnElement(driver, locator, label, 10);

			
			guardAndAbortOnUiError(driver, "Post-Click: " + label);

			return true;

		} catch (FlowAbortException fae) {
			throw fae;
		} catch (Exception e) {
			ExtentManager.logError("Error while clicking " + label + ": " + e.getMessage());
			// Final safety net—if any toast appeared during unexpected exception
			//guardAndAbortOnUiError(driver, "Exception during Click: " + label);
			return false;
		}
	}

	
	/**
	 * Checks for any UI error toast(s). If found: - logs each error, - captures a
	 * screenshot, - navigates to Home (optional), - aborts the entire flow.
	 */
	
	public void guardAndAbortOnUiError(WebDriver driver, String methodName) {
		String errors = captureUIErrorIfPresent(driver);
		if (errors != null && !errors.trim().isEmpty()) {
			// 📸 Screenshot included in captureUIErrorIfPresent only if toast exists
			ExtentManager.logFail("Error detected during: " + methodName + " | Errors: " + errors);

			// 🔥 STOP FLOW
			throw new FlowAbortException("Flow aborted at '" + methodName + "' due to UI errors: " + errors, null);
		}
	}



	// =========================================================================
    // sendkeysToElement1
    // =========================================================================

    /**
     * Waits for the loader, validates visibility, clicks the element, then types
     * the given input string into it.
     *
     * @param driver    active WebDriver instance
     * @param locator   By locator of the target input element
     * @param fieldName human-readable field name (for logging)
     * @param input     text to type into the element
     * @return true if the value was successfully entered, false otherwise
     */
    public boolean sendkeysToElement1(WebDriver driver, By locator, String fieldName, String input) {
        try {
            // FIX: Replaced inline prop.getProperty("loderIsDisplayed") with LOADER_LOCATOR.
            isInvisibleLoader(driver, locator);

            if (!validateElementIsVisible(driver, locator, fieldName)) {
                ExtentManager.logInfo(fieldName + " is not visible");
                return false;
            }

            boolean clicked = clickOnElement(driver, locator, fieldName, SHOTW);
            if (!clicked) {
                ExtentManager.logInfo("Failed to click on " + fieldName);
                return false;
            }

            // FIX: Was waitForElements(driver, 50). A 50-second timeout for an
            // element already confirmed visible and clicked is excessive — any failure
            // would hang the test for nearly a minute. Replaced with DEFAULT_TIMEOUT.
            waitForElements(driver, SHOTW)
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .sendKeys(input);

            // FIX: Was waitForSeconds(3) with comment "wait to ENTER". Both wrong:
            // this method sends text, not ENTER (that comment belonged to
            // sendKeysEnterToElement2), and 3s is too long a blind sleep after typing.
            // Reduced to 1s. Increase only if the field triggers async validation
            // that genuinely needs more settling time.
            waitForSeconds(1);

            ExtentManager.logInfo("Entered value '" + input + "' on " + fieldName);
            return true;

        } catch (Exception e) {
            ExtentManager.logError(
                "Exception while entering value on " + fieldName + ": " + e.getMessage());
            return false;
        }
    }

	
    // =========================================================================
    // sendKeysEnterToElement2
    // =========================================================================

    /**
     * Waits for the loader, validates visibility, clicks the element, then sends
     * the ENTER key to it.
     *
     * @param driver    active WebDriver instance
     * @param locator   By locator of the target element
     * @param fieldName human-readable field name (for logging)
     * @return true if ENTER was successfully sent, false otherwise
     */
    public boolean sendKeysEnterToElement2(WebDriver driver, By locator, String fieldName) {
        try {
            // FIX: Replaced inline prop.getProperty("loderIsDisplayed") with LOADER_LOCATOR.
            isInvisibleLoader(driver, locator);

            if (!validateElementIsVisible(driver, locator, fieldName)) {
                ExtentManager.logInfo(fieldName + " is not visible");
                return false;
            }

            boolean clicked = clickOnElement(driver, locator, fieldName, SHOTW);
            if (!clicked) {
                ExtentManager.logInfo("Failed to click on " + fieldName);
                return false;
            }

            // FIX: Replaced undefined constant SHOTW with DEFAULT_TIMEOUT.
            // SHOTW was never declared anywhere in the code provided — either a
            // compile error or an opaquely named constant defined elsewhere.
            waitForElements(driver, SHOTW)
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .sendKeys(Keys.ENTER);

            waitForSeconds(2);

            ExtentManager.logInfo("Pressed ENTER on " + fieldName);
            return true;

        } catch (Exception e) {
            ExtentManager.logError(
                "Exception while pressing ENTER on " + fieldName + ": " + e.getMessage());
            return false;
        }
    }
    
 // =========================================================================
    // selectFromDropdown
    // =========================================================================

    /**
     * Opens a dropdown by clicking its trigger element, waits for the list to
     * render, then clicks the first item whose visible text matches
     * {@code targetText} (case-insensitive).
     *
     * <p>This helper eliminates the open-find-match-click pattern that was
     * duplicated verbatim in every dropdown method across the page classes.</p>
     *
     * <p>Usage example:</p>
     * <pre>
     *   return selectFromDropdown(driver, accountNameField, accountNameList,
     *                             "Account", "Quality_Assurance");
     * </pre>
     *
     * @param driver         active WebDriver instance
     * @param triggerLocator By locator of the element that opens the dropdown
     * @param listLocator    By locator matching the &lt;li&gt; option elements
     * @param fieldName      human-readable field name (for logging)
     * @param targetText     option label to select (matched case-insensitively)
     * @return true if the option was found and clicked, false otherwise
     */
    public boolean selectFromDropdown(WebDriver driver, By triggerLocator, By listLocator,
            String fieldName, String targetText) {

        boolean isClicked = clickOnField(driver, triggerLocator, fieldName, "Dropdown field");
        if (!isClicked) {
            return false;
        }

        // Wait for the dropdown list to finish its open animation before querying
        // items — findElements returns an empty list if called before rendering.
        waitForSeconds(1);

        List<WebElement> items = driver.findElements(listLocator);
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(targetText)) {
                item.click();
                ExtentManager.logInfo(
                    "Selected '" + targetText + "' from " + fieldName + " dropdown.");
                return true;
            }
        }

        ExtentManager.logFail(
            "Option '" + targetText + "' not found in " + fieldName + " dropdown.");
        return false;
    }



///////////////////////////////////////////////////////////

	// Toast locator (same as yours)
	private static final By TOAST_MESSAGE = By.xpath("//div[contains(@class,'e-toast-content')]");

	// Non-throwing check: returns true if any toast is visible within 1s
	public static boolean isErrorToastPresent(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			List<WebElement> toasts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TOAST_MESSAGE));
			return toasts != null && !toasts.isEmpty();
		} catch (TimeoutException e) {
			return false;
		} catch (Exception e) {
			ExtentManager.logError("Error while checking toast presence: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Captures UI error toast(s) text if present within 3s. Returns error string if
	 * found, otherwise null. Takes screenshot ONLY when toast(s) appear.
	 */
	public static String captureUIErrorIfPresent(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			List<WebElement> toasts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TOAST_MESSAGE));
			if (toasts == null || toasts.isEmpty()) {
				return null;
			}

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
		} catch (Exception e) {
			ExtentManager.logError("Error while capturing UI toast: " + e.getMessage());
			return null;
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

	// Calling again after buffer time.
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