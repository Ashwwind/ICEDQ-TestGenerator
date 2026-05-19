package com.qa.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

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

    // Time variables
    public static long startTimeMillis;
    public static long endTimeMillis;

    public static String timesp;
    public static String endTime;

    @BeforeSuite(alwaysRun = true)
    protected void startSuite() {

        // Capture suite START time
        startTimeMillis = System.currentTimeMillis();

        timesp = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy")
                .format(new Date(startTimeMillis));

        System.out.println("Suite started at: " + timesp);

        // Load locators
        PageUtil.locatotFind();

        // Initialize report
        ExtentManager.getExtentReports();

        // Driver setup
        driver = DriverSetup.initDriver();
        DriverFactory.setDriver(driver);

        // Jenkins / Local handling
        String host = System.getProperty("host");
        String port = System.getProperty("port");

        if (host != null && port != null) {

            baseUrl = "https://" + host + ":" + port + "/";
            homePageUrl = "https://" + host + ":" + port + "/";

            System.out.println("[Jenkins] Launching URL: " + baseUrl);
            System.out.println("[Jenkins] Redirecting URL: " + homePageUrl);

        } else {

            baseUrl = ConfigReader.getProperty("baseUrl");
            homePageUrl = ConfigReader.getProperty("homePageUrl");

            System.out.println("[Local] Launching URL: " + baseUrl);
            System.out.println("[Local] Redirecting URL: " + homePageUrl);
        }

        driver.get(baseUrl);
        driver.get(homePageUrl);
    }

    @AfterSuite(alwaysRun = true)
    public void endSuite() {

        // Capture suite END time
        endTimeMillis = System.currentTimeMillis();

        endTime = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy")
                .format(new Date(endTimeMillis));

        System.out.println("Suite ended at: " + endTime);

        // Flush report
        ExtentManager.flushReport();

        // Close browser
        if (driver != null) {
            driver.quit();
        }

        // Send Email
        String latestReport = SendMail.getLatestExtentReportPath();
        SendMail.sendExecutionReport(latestReport);

        // Print counts
        System.out.println("Passed: " + ExtentReportListener.passedCount);
        System.out.println("Failed: " + ExtentReportListener.failedCount);
        System.out.println("Skipped: " + ExtentReportListener.skippedCount);
    }

    // Get driver
    public WebDriver getDriver() {
        return driver;
    }

    // Set driver
    public void setDriver(WebDriver driver) {
        Base.driver = driver;
    }
}