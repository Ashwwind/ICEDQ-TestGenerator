package com.qa.base;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.utils.DriverSetup;
import com.qa.utils.PageUtil;

public class Base {

	public static WebDriver driver;
	// private static final Logger log = LogManager.getLogger(Base.class);

	public static String buildId = "";
	@BeforeSuite(alwaysRun = true)
	protected void startSuite() {

		// Load locators once for the suite
		PageUtil.locatotFind();

		// Initialize report
		ExtentManager.getExtentReports();

		// Launch browser
		driver = DriverSetup.initDriver();
		driver.get(ConfigReader.getProperty("baseUrl"));
	}

	@AfterSuite(alwaysRun = true)
	public void endSuite() {
		// Flush ExtentReport at the end of the suite
		ExtentManager.flushReport();

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
	
//	public static void extentReportLog(String message) {
//		
//		ExtentListener.test.get().log(Status.INFO, MarkupHelper.createLabel(message, ExtentColor.TRANSPARENT));
//		System.out.println(message);
//		
//	}
//	
//	public static void extentReportPass(String message) {
//		
//		ExtentListener.test.get().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
//		System.out.println(message);
//	}
//	
//	
//	public static void extentReportFail(WebDriver driver, String message, String screenshotName) {
//		System.err.println(message);
//		ExtentListener.test.get().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
//				
//		String screenshot = captureScreenshot(driver, screenshotName);
//		if (screenshot != null) {
//			try {
//				ExtentListener.test.get().fail("Screenshot:",
//						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//		}
	
	// Capture screenshot
//		public static String captureScreenshot(WebDriver driver, String methodName) {
//			try {
//				
//				if(driver == null) return "";
//				
//				String timestamp = new SimpleDateFormat("HH.mm.ss-MM-dd-yyyy").format(new Date());
//				TakesScreenshot ts = (TakesScreenshot) driver;
//
//				String base64 = ts.getScreenshotAs(OutputType.BASE64);
//
//				File src = ts.getScreenshotAs(OutputType.FILE);
//				String folderPath = Paths.get("").toAbsolutePath().toString() + "/reports/" + buildId + "/Screenshots/"
//						+ methodName;
//				File folder = new File(folderPath);
//				if (!folder.exists())
//					folder.mkdirs();
//
//				File dest = new File(folder + "/" + methodName + "-" + timestamp + ".png");
//				FileUtils.copyFile(src, dest);
//
//				return base64;
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("Screenshot failed: " + e.getMessage());
//				return null;
//			}
//		}
	
}
