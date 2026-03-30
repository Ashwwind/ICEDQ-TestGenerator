package com.qa.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.extentreportlistener.ExtentReportListener;
import com.qa.factory.DriverFactory;
import com.qa.sendmail.SendMail;
import com.qa.utils.DriverSetup;
import com.qa.utils.PageUtil;

public class Base {

	public static WebDriver driver;
	public static String baseUrl;
    public static String homePageUrl;

	//public static String buildId = "";
	public static String timesp; // Suite start time
	public static String endTime; // Suite end time

	@BeforeSuite(alwaysRun = true)
	protected void startSuite() {

		// Load locators once for the suite
		PageUtil.locatotFind();
		
		// Driver setup
		 DriverFactory.setDriver(driver);

		// Initialize report
		ExtentManager.getExtentReports();

		// Launch browser
		
		//************************************************************************************//
				//This will run on machine code.
//		driver = DriverSetup.initDriver();
//		driver.get(ConfigReader.getProperty("baseUrl"));
		
		// Launch browser
		//************************************************************************************//
				//This will run on Jenkins code.
//	    driver = DriverSetup.initDriver();
//
//	    String host = System.getProperty("host");
//	    String port = System.getProperty("port");
//
//	     baseUrl = "https://" + host + ":" + port + "/";
//	     homePageUrl = "https://" + host + ":" + port + "/";
//
//	    System.out.println("Launching URL: " + baseUrl);
//
//	    driver.get(baseUrl);
//	    driver.get(homePageUrl);
//
//		// Initialize start timestamp
//		timesp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//		System.out.println("Suite started at: " + timesp);
		
		
		// Generic solution 
		
		driver = DriverSetup.initDriver();

        // Generic: use Jenkins system properties if available, else fall back to config file
        String host = System.getProperty("host");
        String port = System.getProperty("port");

        if (host != null && port != null) {
            // Running via Jenkins — properties injected at runtime
            baseUrl     = "https://" + host + ":" + port + "/";
            homePageUrl = "https://" + host + ":" + port + "/";
            System.out.println("[Jenkins] Launching URL: " + baseUrl);
            System.out.println("[Jenkins] Redirecting URL: " + homePageUrl);    
        } else {
            // Running locally — read from config.properties
            baseUrl     = ConfigReader.getProperty("baseUrl");
            homePageUrl = ConfigReader.getProperty("homePageUrl");
            System.out.println("[Local] Launching URL: " + baseUrl);
            System.out.println("[Local] Redirecting URL: " + homePageUrl);  
        }
        
        driver.get(baseUrl);
        driver.get(homePageUrl);
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
