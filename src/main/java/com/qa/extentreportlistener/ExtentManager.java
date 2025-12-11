package com.qa.extentreportlistener;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.Base;

public class ExtentManager {
	private static ExtentReports extent;

	private ExtentManager() {
	}

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = ExecutionVariables.suitename + "-Suite-" + timeStamp + ".html";

		ExecutionVariables.reportFileFolder = Base.DIR_PATH + "/reports/" + Base.buildId + "/"
				+ ExecutionVariables.suitename + timeStamp;

		ExecutionVariables.reportFilePath = ExecutionVariables.reportFileFolder + "/" + repName;

		// System.setProperty("chaintest.generator.simple.output-file",
		// ExecutionVariables.reportFilePath);

		ExtentSparkReporter spark = new ExtentSparkReporter(ExecutionVariables.reportFilePath);

		spark.config().setEncoding("utf-8");
		spark.config().setTheme(Theme.STANDARD);

		spark.config().setDocumentTitle("Automation Execution Report");
		spark.config().setReportName(ExecutionVariables.suitename);
		spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a");

		spark.config().setOfflineMode(true); // Embeds CSS, JS, views into one file

		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("Suite Name", ExecutionVariables.suitename);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User", System.getProperty("user.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Environment", System.getProperty("env", "QA"));
		extent.setSystemInfo("Execution Time", timeStamp);

		return extent;
	}

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void setTest(ExtentTest extentTest) {
		test.set(extentTest);
	}

	public static void unload() {
		test.remove();
	}

}
