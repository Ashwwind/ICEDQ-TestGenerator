package com.qa.extentreportlistener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=========== Test Suite Started ===========");
        ExtentManager.getExtentReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=========== Test Suite Finished ===========");
        ExtentManager.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (ExtentManager.test.get() == null) {
            ExtentManager.startTest(result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.logPass("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.logFail("Test FAILED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.logSkip("Test SKIPPED: " + result.getMethod().getMethodName());
    }
}
