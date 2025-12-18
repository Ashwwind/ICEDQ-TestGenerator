package com.qa.extentreportlistener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ExtentManager {

	private static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static String buildId = "BUILD";

	// Initialize report
	public static ExtentReports getExtentReports() {
		if (extent == null) {
			String reportPath = Paths.get("").toAbsolutePath().toString() + "/reports/" + buildId + "/ExtentReport-"
					+ System.currentTimeMillis() + ".html";

			File reportFile = new File(reportPath);
			File reportDir = reportFile.getParentFile();
			if (!reportDir.exists())
				reportDir.mkdirs();

			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Automation Test Report");
			spark.config().setReportName("Execution Results");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}

	// Start test
	public static ExtentTest startTest(String testName) {
		ExtentTest extentTest = getExtentReports().createTest(testName);
		test.set(extentTest);
		System.out.println("Starting Test: " + testName);
		return extentTest;
	}

	// Flush report
	public static void flushReport() {
		if (extent != null)
			extent.flush();
	}

	// Capture screenshot and return Base64 string
	public static String captureScreenshot(String methodName) {
		try {
			String timestamp = new SimpleDateFormat("HH.mm.ss-MM-dd-yyyy").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) Base.driver;

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

	// Logging methods
	public static void logInfo(String message) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String consoleMessage = String.format("[%s] INFO  %s - %s", timestamp, className, message);

		// Log to Extent
		//test.get().log(Status.INFO, MarkupHelper.createLabel(consoleMessage, ExtentColor.BLACK));
		test.get().log(Status.INFO, createLabel(consoleMessage, ExtentColor.BLACK));
		// Print to console
		System.out.println(consoleMessage);
	}

	private static Media createLabel(String consoleMessage, ExtentColor black) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void logPass(String message) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String consoleMessage = String.format("[%s] PASS  %s - %s", timestamp, className, message);

		test.get().log(Status.PASS, MarkupHelper.createLabel(consoleMessage, ExtentColor.GREEN));
		System.out.println(consoleMessage);
	}

	public static void logFail(String message) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String consoleMessage = String.format("[%s] FAIL  %s - %s", timestamp, className, message);

		test.get().log(Status.FAIL, MarkupHelper.createLabel(consoleMessage, ExtentColor.RED));
		System.out.println(consoleMessage);

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

	public static void logSkip(String message) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String consoleMessage = String.format("[%s] SKIP  %s - %s", timestamp, className, message);

		test.get().log(Status.SKIP, MarkupHelper.createLabel(consoleMessage, ExtentColor.ORANGE));
		System.out.println(consoleMessage);
	}
	public static void logError(String message) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String consoleMessage = String.format("[%s] SKIP  %s - %s", timestamp, className, message);

		test.get().log(Status.WARNING, MarkupHelper.createLabel(consoleMessage, ExtentColor.GREY));
		System.out.println(consoleMessage);
	}
}
