package com.qa.extentreportlistener;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import annotations.XrayTest;
import annotations.XrayResultStore;

public class ExtentReportListener implements ITestListener {

    // FIX: AtomicInteger is thread-safe under parallel execution.
    //      The old "public static int" fields were subject to race conditions.
    public static final AtomicInteger passedCount  = new AtomicInteger(0);
    public static final AtomicInteger failedCount  = new AtomicInteger(0);
    public static final AtomicInteger skippedCount = new AtomicInteger(0);


	@Override
	public void onStart(ITestContext context) {
		System.out.println(STR."=========== Test Suite Started: \{context.getName()} ===========");
		XrayResultStore.clearResults();
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(STR."=========== Test Suite Finished: \{context.getName()} ===========");
		ExtentManager.flushReport();

		// Print Xray summary in console & Extent
		ExtentManager.logInfo("===== XRAY TEST SUMMARY =====");
		XrayResultStore.getResults().forEach((k, v) -> {
			System.out.println(STR."Xray Test: \{k} → \{v}");
			ExtentManager.logInfo(STR."Xray Test: \{k} → \{v}");
		});
		ExtentManager.logInfo(STR."Passed: \{passedCount.get()}, Failed: \{failedCount.get()}, Skipped: \{skippedCount.get()}");
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
		ExtentManager.logPass(STR."Test Passed: \{result.getMethod().getMethodName()}");
		passedCount.incrementAndGet();  // thread-safe increment
	}

	@Override
	public void onTestFailure(ITestResult result) {
		handleXray(result, "FAILED");
		ExtentManager.logFail(STR."Test FAILED: \{result.getMethod().getMethodName()}");
		failedCount.incrementAndGet();  // thread-safe increment
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		handleXray(result, "SKIPPED");
		ExtentManager.logSkip(STR."Test SKIPPED: \{result.getMethod().getMethodName()}");
		skippedCount.incrementAndGet();  // thread-safe increment
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
			ExtentManager.logInfo(STR."Mapped Xray Test ID: \{xrayId} → \{status}");
		}
	}
}
