package com.qa.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.extentreportlistener.ExtentReportListener;
import com.qa.sendmail.SendMail;
import com.qa.utils.DriverSetup;
import com.qa.utils.PageUtil;

public class Base {

	public static WebDriver driver;

	//public static String buildId = "";
	public static String timesp; // Suite start time
	public static String endTime; // Suite end time

	@BeforeSuite(alwaysRun = true)
	protected void startSuite() {

		// Load locators once for the suite
		PageUtil.locatotFind();

		// Initialize report
		ExtentManager.getExtentReports();

		// Launch browser
		driver = DriverSetup.initDriver();
		driver.get(ConfigReader.getProperty("baseUrl"));

		// Initialize start timestamp
		timesp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		System.out.println("Suite started at: " + timesp);
	}

	@AfterSuite(alwaysRun = true)
	public void endSuite() {

		// Initialize end timestamp
		endTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		System.out.println("Suite ended at: " + endTime);

		// Flush Extent Report
		ExtentManager.flushReport();

		// Close browser
		if (driver != null) {
			driver.quit();
		}

		// Send Email with Extent Report
		String latestReport = SendMail.getLatestExtentReportPath();
		SendMail.sendExecutionReport(latestReport);
		
		System.out.println("Passed: " + ExtentReportListener.passedCount);
	    System.out.println("Failed: " + ExtentReportListener.failedCount);
	    System.out.println("Skipped: " + ExtentReportListener.skippedCount);
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
