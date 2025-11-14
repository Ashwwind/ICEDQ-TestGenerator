package com.qa.extentreportlistener;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG implements IReporter {

	private ExtentReports extent;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		// Initialize ExtentReports v5
		ExtentSparkReporter spark = new ExtentSparkReporter(outputDirectory + File.separator + "Extent.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				// Build report nodes
				buildTestNodes(context.getPassedTests(), Status.PASS);
				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
			}
		}

		extent.flush(); // Write the report
	}

	private void buildTestNodes(IResultMap tests, Status status) {
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {

				// Create a test in report
				ExtentTest test = extent.createTest(result.getMethod().getMethodName());

				// Assign groups as categories
				for (String group : result.getMethod().getGroups()) {
					test.assignCategory(group);
				}

				// Log exception or pass/fail message
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed successfully");
				}
			}
		}
	}
}
