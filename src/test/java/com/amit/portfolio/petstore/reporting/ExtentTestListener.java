package com.amit.portfolio.petstore.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Simple TestNG listener that generates an Extent Spark HTML report.
 * Output: target/extent-report/ExtentReport.html
 */
public class ExtentTestListener implements ITestListener, ISuiteListener {

    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();
    private static ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        try {
            Path outDir = Paths.get("target", "extent-report");
            Files.createDirectories(outDir);

            ExtentSparkReporter spark = new ExtentSparkReporter(outDir.resolve("ExtentReport.html").toString());
            spark.config().setDocumentTitle("Swagger Petstore API Test Report");
            spark.config().setReportName("REST Assured + TestNG");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Suite", suite.getName());
            extent.setSystemInfo("Java", System.getProperty("java.version"));
            extent.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Extent report", e);
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) return;

        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();

        ExtentTest t = extent.createTest(testName)
                .assignCategory(className);

        TEST.set(t);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest t = TEST.get();
        if (t != null) t.log(Status.PASS, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest t = TEST.get();
        if (t != null) {
            Throwable thr = result.getThrowable();
            if (thr != null) {
                t.log(Status.FAIL, thr);
            } else {
                t.log(Status.FAIL, "Failed");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest t = TEST.get();
        if (t != null) {
            Throwable thr = result.getThrowable();
            if (thr != null) {
                t.log(Status.SKIP, thr);
            } else {
                t.log(Status.SKIP, "Skipped");
            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // Not used
    }

    @Override
    public void onFinish(ITestContext context) {
        // Not used
    }
}
