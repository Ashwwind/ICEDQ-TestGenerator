package com.qa.extentreportlistener;

import java.lang.reflect.Method;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import annotations.XrayTest;
import annotations.XrayResultStore;

public class ExtentReportListener implements ITestListener {

	public static int passedCount = 0;
	public static int failedCount = 0;
	public static int skippedCount = 0;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("=========== Test Suite Started ===========");
		ExtentManager.getExtentReports();
		XrayResultStore.clearResults(); // clear previous results
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("=========== Test Suite Finished ===========");
		ExtentManager.flushReport();

		// ✅ Print Xray summary in console & Extent
		ExtentManager.logInfo("===== XRAY TEST SUMMARY =====");
		XrayResultStore.getResults().forEach((k, v) -> {
			System.out.println("Xray Test: " + k + " → " + v);
			ExtentManager.logInfo("Xray Test: " + k + " → " + v);
		});
		ExtentManager.logInfo("Passed: " + passedCount + ", Failed: " + failedCount + ", Skipped: " + skippedCount);
	}

	@Override
	public void onTestStart(ITestResult result) {
		if (ExtentManager.test.get() == null) {
			ExtentManager.startTest(result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		handleXray(result, "PASSED");
		ExtentManager.logPass("Test Passed: " + result.getMethod().getMethodName());
		passedCount++;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		handleXray(result, "FAILED");
		ExtentManager.logFail("Test FAILED: " + result.getMethod().getMethodName());
		failedCount++;
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		handleXray(result, "SKIPPED");
		ExtentManager.logSkip("Test SKIPPED: " + result.getMethod().getMethodName());
		skippedCount++;
	}

	// ================= XRAY CORE =================
	private void handleXray(ITestResult result, String status) {
		String xrayId = null;

		// Runtime attribute first
		Object attr = result.getAttribute("XRAY_TEST_ID");
		if (attr != null) {
			xrayId = attr.toString();
		}

		// Fallback to annotation
		if (xrayId == null) {
			Method method = result.getMethod().getConstructorOrMethod().getMethod();
			if (method != null && method.isAnnotationPresent(XrayTest.class)) {
				xrayId = method.getAnnotation(XrayTest.class).value();
			}
		}

		if (xrayId != null) {
			XrayResultStore.addResult(xrayId, status);
			ExtentManager.logInfo("Mapped Xray Test ID: " + xrayId + " → " + status);
		}
	}
}
