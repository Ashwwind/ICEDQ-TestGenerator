package com.qa.extentreportlistener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
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

    // ===================== INIT REPORT =====================
    public static ExtentReports getExtentReports() {

        if (extent == null) {

            String reportPath = Paths.get("").toAbsolutePath()
                    + "/reports/" + buildId + "/ExtentReport-" + System.currentTimeMillis() + ".html";

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
    public static ExtentTest startTest(String testName) {
        ExtentTest extentTest = getExtentReports().createTest(testName);
        test.set(extentTest);
        System.out.println("▶ Starting Test: " + testName);
        return extentTest;
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
            String timestamp =
                    new SimpleDateFormat("HH.mm.ss-dd-MM-yyyy").format(new Date());

            TakesScreenshot ts = (TakesScreenshot) Base.driver;
            String base64 = ts.getScreenshotAs(OutputType.BASE64);

            File src = ts.getScreenshotAs(OutputType.FILE);
            String folderPath = Paths.get("").toAbsolutePath()
                    + "/reports/" + buildId + "/Screenshots/" + name;

            File folder = new File(folderPath);
            folder.mkdirs();

            File dest = new File(folder, name + "-" + timestamp + ".png");
            FileUtils.copyFile(src, dest);

            return base64;

        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }

    // ===================== LOG INFO =====================
    public static void logInfo(String message) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String className =
                Thread.currentThread().getStackTrace()[2].getClassName();

        String log = String.format("[%s] INFO  %s - %s",
                timestamp, className, message);

        test.get().log(Status.INFO,
                MarkupHelper.createLabel(log, ExtentColor.BLUE));

        System.out.println(log);
    }

    // ===================== LOG PASS =====================
    public static void logPass(String message) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String className =
                Thread.currentThread().getStackTrace()[2].getClassName();

        String log = String.format("[%s] PASS  %s - %s",
                timestamp, className, message);

        test.get().log(Status.PASS,
                MarkupHelper.createLabel(log, ExtentColor.GREEN));

        System.out.println(log);
    }

    // ===================== LOG FAIL =====================
    public static void logFail(String message) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String className =
                Thread.currentThread().getStackTrace()[2].getClassName();

        String log = String.format("[%s] FAIL  %s - %s",
                timestamp, className, message);

        test.get().log(Status.FAIL,
                MarkupHelper.createLabel(log, ExtentColor.RED));

        System.out.println(log);

        // Attach screenshot ONCE
        String screenshot = captureScreenshot(test.get().getModel().getName());

        if (screenshot != null) {
            test.get().fail("Screenshot",
                    MediaEntityBuilder
                            .createScreenCaptureFromBase64String(screenshot)
                            .build());
        }
    }

    // ===================== LOG SKIP =====================
    public static void logSkip(String message) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String className =
                Thread.currentThread().getStackTrace()[2].getClassName();

        String log = String.format("[%s] SKIP  %s - %s",
                timestamp, className, message);

        test.get().log(Status.SKIP,
                MarkupHelper.createLabel(log, ExtentColor.ORANGE));

        System.out.println(log);
    }

    // ===================== LOG UI ERROR =====================
    public static void logError(String message) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String className =
                Thread.currentThread().getStackTrace()[2].getClassName();

        String log = String.format("[%s] ERROR %s - %s",
                timestamp, className, message);

        test.get().log(Status.WARNING,
                MarkupHelper.createLabel(log, ExtentColor.GREY));

        System.out.println(log);
    }
}
