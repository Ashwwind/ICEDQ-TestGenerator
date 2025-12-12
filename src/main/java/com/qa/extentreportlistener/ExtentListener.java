//package com.qa.extentreportlistener;
//
//import com.aventstack.extentreports.*;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.qa.base.Base;
//
//import org.openqa.selenium.*;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import org.apache.commons.io.FileUtils;
//
//import java.io.File;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ExtentListener implements ITestListener {
//
//	private static ExtentReports extent;
//	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//	public static String buildId = "BUILD";
//
//	// Initialize report
//	public static void initReport() {
//		if (extent == null) {
//			String reportPath = Paths.get("").toAbsolutePath().toString() + "/reports/" + buildId + "/ExtentReport-"
//					+ System.currentTimeMillis() + ".html";
//
//			File reportFile = new File(reportPath);
//			File reportDir = reportFile.getParentFile();
//			if (!reportDir.exists()) {
//				reportDir.mkdirs();
//			}
//
//			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
//			spark.config().setTheme(Theme.DARK);
//			spark.config().setDocumentTitle("Automation Test Report");
//			spark.config().setReportName("Execution Results");
//			spark.config().setTheme(Theme.STANDARD);
//
//			extent = new ExtentReports();
//			extent.attachReporter(spark);
//
//			extent.setSystemInfo("OS", System.getProperty("os.name"));
//			extent.setSystemInfo("Environment", "QA");
//		}
//	}
//
//	// Flush report
//	public static void flushReport() {
//		if (extent != null) {
//			extent.flush();
//		}
//	}
//
//	// Start test
//	public static void startTest(String testName) {
//		ExtentTest extentTest = extent.createTest(testName);
//		test.set(extentTest);
//		System.out.println("Starting Test: " + testName);
//	}
//
//	// Log test pass
//	public static void pass(String message) {
//		test.get().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
//	}
//
//	// Log test fail with screenshot
//	public static void fail(String message) {
//		test.get().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
//		String screenshot = captureScreenshot(test.get().getModel().getName());
//		if (screenshot != null) {
//			try {
//				test.get().fail("Screenshot:",
//						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static void failWithScreenshot(String message, String base64) {
//		test.get().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
//
//		try {
//			test.get().fail("Screenshot:", MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// Log test skipped
//	public static void skip(String message) {
//		test.get().log(Status.SKIP, MarkupHelper.createLabel(message, ExtentColor.ORANGE));
//	}
//
//	// Capture screenshot
//	public static String captureScreenshot(String methodName) {
//		try {
//			String timestamp = new SimpleDateFormat("HH.mm.ss-MM-dd-yyyy").format(new Date());
//			TakesScreenshot ts = (TakesScreenshot) Base.driver;
//
//			String base64 = ts.getScreenshotAs(OutputType.BASE64);
//
//			File src = ts.getScreenshotAs(OutputType.FILE);
//			String folderPath = Paths.get("").toAbsolutePath().toString() + "/reports/" + buildId + "/Screenshots/"
//					+ methodName;
//			File folder = new File(folderPath);
//			if (!folder.exists())
//				folder.mkdirs();
//
//			File dest = new File(folder + "/" + methodName + "-" + timestamp + ".png");
//			FileUtils.copyFile(src, dest);
//
//			return base64;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/*
//	 * ===================================================== TESTNG @Override
//	 * METHODS BELOW =====================================================
//	 */
//
//	@Override
//	public void onStart(ITestContext context) {
//		System.out.println("=========== Test Suite Started ===========");
//		initReport();
//	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//		System.out.println("=========== Test Suite Finished ===========");
//		flushReport();
//	}
//
//	@Override
//	public void onTestStart(ITestResult result) {
//		// If no test already created (manually), create with method name
//		if (test.get() == null) {
//			startTest(result.getMethod().getMethodName());
//		}
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		pass("Test Passed: " + result.getMethod().getMethodName());
//	}
//
//	@Override
//	public void onTestFailure(ITestResult result) {
//		fail("Test FAILED: " + result.getMethod().getMethodName());
//	}
//
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		skip("Test SKIPPED: " + result.getMethod().getMethodName());
//	}
//
//}
