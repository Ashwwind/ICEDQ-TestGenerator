package com.qa.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.qa.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger log = LogManager.getLogger(Base.class);
    private static final String TIME_FORMAT = "HH:mm:ss - dd/MM/yyyy";

    // ── NOTE: driver is NOT static. Each test thread gets its own instance
    //         via DriverFactory.getDriver() (ThreadLocal).
    //         Pages that still reference Base.driver should be migrated to
    //         DriverFactory.getDriver() over time.
    public static WebDriver driver;

    public static String baseUrl;
    public static String homePageUrl;

    // Suite timing
    public static long startTimeMillis;
    public static long endTimeMillis;
    public static String timesp;  // human-readable start time
    public static String endTime; // human-readable end time

    // ────────────────────────────────────────────────────────────────────────────

    @BeforeSuite(alwaysRun = true)
    protected void startSuite() {

        // 1. Capture suite START time
        startTimeMillis = System.currentTimeMillis();
        timesp = new SimpleDateFormat(TIME_FORMAT).format(new Date(startTimeMillis));
        log.info("Suite started at: {}", timesp);

        // 2. Load all locator .properties files into PageUtil.prop
        PageUtil.locatotFind();

        // 3. Initialise ExtentReports (idempotent — safe to call multiple times)
        ExtentManager.getExtentReports();

        // 4. Start browser
        driver = DriverSetup.initDriver();
        DriverFactory.setDriver(driver);

        // 5. Resolve base URL — Jenkins system properties override config file
        String host = System.getProperty("host");
        String port = System.getProperty("port");

        if (host != null && !host.isBlank() && port != null && !port.isBlank()) {
            baseUrl     = "https://" + host + ":" + port + "/";
            homePageUrl = baseUrl;
            log.info("[Jenkins] Launching URL: {}", baseUrl);
        } else {
            baseUrl     = ConfigReader.getProperty("baseUrl");
            homePageUrl = ConfigReader.getProperty("homePageUrl");
            log.info("[Local] Launching URL: {}", baseUrl);
            log.info("[Local] Home page URL: {}", homePageUrl);
        }

        // FIX: removed duplicate driver.get(baseUrl) — only navigate to homePageUrl once.
        // If baseUrl != homePageUrl, the old code opened baseUrl then immediately
        // navigated away, wasting 3-5 seconds on an extra full page load.
        driver.get(homePageUrl);
    }

    // ────────────────────────────────────────────────────────────────────────────

    @AfterSuite(alwaysRun = true)
    public void endSuite() {

        // 1. Capture suite END time
        endTimeMillis = System.currentTimeMillis();
        endTime = new SimpleDateFormat(TIME_FORMAT).format(new Date(endTimeMillis));
        log.info("Suite ended at: {}", endTime);

        // 2. Logout
//        if (driver != null) {
//            try {
//                LoginPage loginPage = new LoginPage(driver);
//                loginPage.logout();
//            } catch (Exception e) {
//                log.warn("Logout skipped: {}", e.getMessage());
//            }
//        }

        // 3. Flush ExtentReport to disk
        ExtentManager.flushReport();

        // 4. Quit browser
        if (driver != null) {
            driver.quit();
            DriverFactory.removeDriver();
        }

        // 5. Email the report
        String latestReport = SendMail.getLatestExtentReportPath();
        SendMail.sendExecutionReport(latestReport);

        // 6. Print pass/fail/skip summary
        log.info("Results — Passed: {} | Failed: {} | Skipped: {}",
                ExtentReportListener.passedCount.get(),
                ExtentReportListener.failedCount.get(),
                ExtentReportListener.skippedCount.get());
    }

}


