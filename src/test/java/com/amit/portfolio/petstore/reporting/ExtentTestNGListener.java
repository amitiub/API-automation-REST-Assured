package com.amit.portfolio.petstore.reporting;

import com.aventstack.extentreports.ExtentTest;
import org.testng.*;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static final ThreadLocal<ExtentTest> TL = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        ExtentManager.getInstance(); // initialize
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getTestClass().getName() + " :: " + result.getMethod().getMethodName();
        ExtentTest test = ExtentManager.getInstance().createTest(name);
        TL.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = TL.get();
        if (test != null) test.pass("PASSED");
        TL.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = TL.get();
        if (test != null) {
            Throwable t = result.getThrowable();
            if (t != null) test.fail(t);
            else test.fail("FAILED");
        }
        TL.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = TL.get();
        if (test != null) {
            Throwable t = result.getThrowable();
            if (t != null) test.skip(t);
            else test.skip("SKIPPED");
        }
        TL.remove();
    }
}
