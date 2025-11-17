package com.qa.extentreportlistener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.qa.base.Base;


public class ExtentListener implements ITestListener {

	private static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.get().fail(result.getThrowable());

		try {
			WebDriver driver = ((Base) result.getInstance()).getDriver();
			String screenshot = captureScreenshot(driver, result.getMethod().getMethodName());
			test.get().addScreenCaptureFromBase64String(screenshot);
		} catch (Exception e) {
			test.get().warning("Screenshot capture failed: " + e.getMessage());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed Successfully");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	// Optional - Add screenshot utility here
	public static String captureScreenshot(WebDriver driver, String testName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}

}
