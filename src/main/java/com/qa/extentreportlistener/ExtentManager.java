package com.qa.extentreportlistener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static String buildId = "BUILD";
    
    // ===== CONSOLE TIMESTAMP =====
    private static String consoleTimestamp() {
    	return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // ===================== INIT REPORT =====================
    public static ExtentReports getExtentReports() {

        if (extent == null) {

            String reportPath = STR."\{Paths.get("").toAbsolutePath()}/reports/\{buildId}/TestGenerator Test Execution-Suite-\{System.currentTimeMillis()}.html";

            File reportFile = new File(reportPath);
            reportFile.getParentFile().mkdirs();

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

    // ===================== START TEST =====================
    public static void startTest(String testName) {
        ExtentTest extentTest = getExtentReports().createTest(testName);
        test.set(extentTest);
        System.out.println(STR."▶ Starting Test: \{testName}");
    }

    // ===================== FLUSH =====================
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // ===================== SCREENSHOT =====================
    public static String captureScreenshot(String name) {
        try {
            // Sanitize name
            String safeName = name.replaceAll("[\\\\/:*?\"<>|]", "_");

            // Timestamp
            String timestamp = new SimpleDateFormat("HH.mm.ss-dd-MM-yyyy").format(new Date());

            TakesScreenshot ts = (TakesScreenshot) Base.driver;

            // ✅ Take screenshot ONLY ONCE
            File src = ts.getScreenshotAs(OutputType.FILE);

            // Build folder path
            Path folderPath = Paths.get("reports", buildId, "Screenshots", safeName);
            File folder = folderPath.toFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Build destination file
            File destination = folderPath.resolve(STR."\{safeName}-\{timestamp}.png").toFile();

            // Copy file
            FileUtils.copyFile(src, destination);

            // ✅ Convert file to Base64 (no second screenshot)
            byte[] fileContent = Files.readAllBytes(destination.toPath());

            return Base64.getEncoder().encodeToString(fileContent);

        } catch (Exception e) {
            System.out.println(STR."Screenshot capture failed: \{e.getMessage()}");
            return null;
        }
    }

    // ===================== LOG INFO =====================
	public static void logInfo(String message) {

		// Extent → ONLY message
		 test.get().info(STR."<span style='color:black'>\{message}</span>");

		// Console → full details
		System.out.println(STR."[\{consoleTimestamp()}] INFO \{Thread.currentThread().getStackTrace()[2].getClassName()} - \{message}");
	}




	// ===================== LOG PASS =====================
	public static void logPass(String message) {
        // rounded corners relative to font
        // compact vertical height
        // box hugs text, no full-row width
        String html = STR."<span style='background-color: #a8e6a1;color:#000;padding:0.25em 0.6em;border-radius:0.4em;font-weight:500;line-height:1.2;display:inline-block;max-width:100%;white-space:normal;word-break:break-word;'>\{message}</span>";

		test.get().pass(html);

		System.out.println(STR."[\{consoleTimestamp()}] PASS \{Thread.currentThread().getStackTrace()[2].getClassName()} - \{message}");
	}

	// ===================== LOG FAIL =====================
	public static void logFail(String message) {
		String html = STR."<span style='background-color:#dc3545;color:#000;padding:0.25em 0.6em;border-radius:0.4em;font-weight:500;line-height:1.2;display:inline-block;max-width:100%;white-space:normal;word-break:break-word;'>\{message}</span>";

		test.get().fail(html);

		System.out.println(STR."[\{consoleTimestamp()}] FAIL \{Thread.currentThread().getStackTrace()[2].getClassName()} - \{message}");

		String screenshot = captureScreenshot(test.get().getModel().getName());
		if (screenshot != null) {
			test.get().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}
	}

	// ===================== LOG SKIP =====================
	public static void logSkip(String message) {

		String className = Thread.currentThread().getStackTrace()[2].getClassName();

		// Extent → message only (standard size)
		test.get().skip(STR."<span style='color:gray; font-weight:normal;'>\{message}</span>");

		// Console → timestamp + status + class
		System.out.println(STR."[\{consoleTimestamp()}] SKIP  \{className} - \{message}");
	}

	// ===================== LOG UI ERROR =====================
	public static void logError(String message) {

		String className = Thread.currentThread().getStackTrace()[2].getClassName();

		// Extent → message only (standard size)
		test.get().warning(STR."<span style='color:orange; font-weight:normal;'>\{message}</span>");

		// Console → timestamp + status + class
		System.out.println(STR."[\{consoleTimestamp()}] ERROR \{className} - \{message}");
	}

}
