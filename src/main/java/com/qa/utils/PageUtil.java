package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.yaml.snakeyaml.scanner.Constant;

import com.aventstack.extentreports.Status;
import com.qa.extentreportlistener.ExtentListener;

public class PageUtil {

	private static final Logger log = LogManager.getLogger(PageUtil.class);
	public static final int SHOTW = 50; 
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
			isInvisibleLoader(driver, By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div"));
			isInvisibleLoader(driver, By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div"));
			return waitForElements(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by))
					.isDisplayed();
		} catch (TimeoutException te) {
			return new WebDriverWait(driver, Duration.ofSeconds(timeout + 20))
					.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed(); // fallback technique
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.INFO,"Element is not visiblle");
		}
		return false;

	}

	// Wait for the element to be clickable.
	public static void waitForTheElementToBeClickable(WebDriver driver, By by, String label) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			ExtentListener.test.get().log(Status.INFO,"Clicking on " + label);
			driver.findElement(by).click();
			ExtentListener.test.get().log(Status.INFO,"Clicked on " + label );
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.INFO,"Element is not clickable :" + by );
		}
	}

	public static void clickOnElement(WebDriver driver, By by, int timeout) {
		try {

			boolean state = isDisplayed(driver, by, timeout);
			if (state) {
				waitForElements(driver, timeout).until(ExpectedConditions.elementToBeClickable(by)).click();
				ExtentListener.test.get().log(Status.INFO, "Element is clickable "  );
			} else {
				log.info("Element not visible : " + by);
			}
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.INFO,"Element is not clickable " + by);
		}
	}
	
	// Validate the element is visible or not.
	
	public static boolean validateElementIsVisible(WebDriver driver, By locator, String label, boolean expected) {
		boolean flag=true;
		
		if(isDisplayed(driver, locator, 10) && expected) {
			ExtentListener.test.get().log(Status.PASS,label + " is visible as expected : " + expected);
		}else if(expected){
			ExtentListener.test.get().log(Status.FAIL,label + " is not visible as expected : " + expected);
			flag=false;
		}else {
			ExtentListener.test.get().log(Status.FAIL,label + " is not visible as expected : " + expected);
		}
		return flag;
	}
	
	// Validate the button element is visible or not.
	
//	public boolean verifyVisibilityOfButtons(WebDriver driver, By locator, String buttonName, boolean expected) {
//		  SoftAssert soft = new SoftAssert();
//		  boolean status = false;
//		  try {
//
//		   BaseSuite.reportLog("Verifying for button " + buttonName);
//
//		   isDisplayed(driver, Constant.SHOTW, locator);
//
//		   isDisplayed(driver, 1, locator);
//
//		   if (isDisplayed(driver, locator)) {
//		    boolean b = isEnabled(driver, locator);
//		    if (b) {
//		     status = true;
//
//		     BaseSuite.validationReportLog(buttonName + " button is enabled in the page");
//		    } else {
//
//		     BaseSuite.validationReportLog(buttonName + " button is disabled in the page");
//		    }
//		    soft.assertEquals(status, expected, buttonName + " button is expected to be " + expected
//		      + " & actual status of button as " + status + " :: ");
//		    boolean isEqual = (status == expected);
//		    if (isEqual) {
//		     status = true;
//		     BaseSuite.validationReportLog(buttonName + " button visibility is matched with expected condition");
//		    } else {
//		     BaseSuite.reportFailLog(buttonName + " button is expected to be " + expected
//		       + " & actual status of button as " + status, "verifyVisibilityOfButtons");
//		     status =false;
//		    }
//		   } else {
//		    BaseSuite.reportFailLog(buttonName + " button is not visible", "verifyVisibilityOfButtons");
//
//		   }
//		  } catch (Exception e) {
//		   BaseSuite.reportErrorLog(e,"verifyVisibilityOfButtons :: " + e.getMessage());
//		  }
//
//		  return status;
//		 }
//	

	//
	public static void isInvisibleLoader(WebDriver driver, By locator) {
		waitForElements(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Enter data in the filed.
	public static void sendkeysToElement(WebDriver driver, By locator, String label, String input) {
		clickOnElement(driver, locator, 10);
		ExtentListener.test.get().log(Status.INFO,"Entering value on " + label + " input : " + input);
		waitForElements(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(input);
		ExtentListener.test.get().log(Status.PASS,"Entered value on " + label + " input : " + input);
	}

	// Enter data in the filed.
	public static void sendkeysToEnter(WebDriver driver, By locator, String label) {
		clickOnElement(driver, locator, 10);
		ExtentListener.test.get().log(Status.INFO,"Clicking enter on " + label);
		waitForElements(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(Keys.ENTER);
		ExtentListener.test.get().log(Status.INFO,"Clicked enter on " + label);
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
