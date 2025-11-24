package com.qa.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.utils.DriverSetup;
import com.qa.utils.PageUtil;

public class Base {

	public static WebDriver driver;
	// private static final Logger log = LogManager.getLogger(Base.class);

	@BeforeSuite(alwaysRun = true)
	protected void startSuite() {

		// Load locators once for the suite
		PageUtil.locatotFind();

		// Initialize report
		ExtentListener.initReport();

		// Launch browser
		driver = DriverSetup.initDriver();
		driver.get(ConfigReader.getProperty("baseUrl"));
	}

	@AfterSuite(alwaysRun = true)
	public void endSuite() {
		// Flush ExtentReport at the end of the suite
		ExtentListener.flushReport();

		// Close the browser
		if (driver != null) {
			driver.quit();
		}
	}

	// Get driver method
	public WebDriver getDriver() {
		return driver;
	}

	// Set driver method
	public void setDriver(WebDriver driver) {
		Base.driver = driver;
	}
}
