package com.qa.extentreportlistener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentListener implements ITestListener{

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static WebDriver driver;
	public static String buildId = "BUILD_" + System.currentTimeMillis();

	// Initialize report
	public static void initReport() {
		if (extent == null) {
			String reportPath = Paths.get("").toAbsolutePath().toString() + "/reports/" + buildId
					+ "/ExtentReport.html";

			File reportFile = new File(reportPath);
			File reportDir = reportFile.getParentFile();
			if (!reportDir.exists()) {
				reportDir.mkdirs();
			}

			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setDocumentTitle("Automation Test Report");
			spark.config().setReportName("Execution Results");
			spark.config().setTheme(Theme.STANDARD);

			extent = new ExtentReports();
			extent.attachReporter(spark);

			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Environment", "QA");
		}
	}

	// Flush report
	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}

	// Start test
	public static void startTest(String testName) {
		ExtentTest extentTest = extent.createTest(testName);
		test.set(extentTest);
		System.out.println("Starting Test: " + testName);
	}

	// Log test pass
	public static void pass(String message) {
		test.get().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}

	// Log test fail with screenshot
	public static void fail(String message) {
		test.get().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
		String screenshot = captureScreenshot(test.get().getModel().getName());
		if (screenshot != null) {
			try {
				test.get().fail("Screenshot:",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Log test skipped
	public static void skip(String message) {
		test.get().log(Status.SKIP, MarkupHelper.createLabel(message, ExtentColor.ORANGE));
	}

	// Capture screenshot
	private static String captureScreenshot(String methodName) {
		try {
			String timestamp = new SimpleDateFormat("HH.mm.ss-MM-dd-yyyy").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;

			String base64 = ts.getScreenshotAs(OutputType.BASE64);

			File src = ts.getScreenshotAs(OutputType.FILE);
			String folderPath = Paths.get("").toAbsolutePath().toString() + "/reports/" + buildId + "/Screenshots/"
					+ methodName;
			File folder = new File(folderPath);
			if (!folder.exists())
				folder.mkdirs();

			File dest = new File(folder + "/" + methodName + "-" + timestamp + ".png");
			FileUtils.copyFile(src, dest);

			return base64;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
