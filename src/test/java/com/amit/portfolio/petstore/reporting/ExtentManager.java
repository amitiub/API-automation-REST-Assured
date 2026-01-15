package com.amit.portfolio.petstore.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public final class ExtentManager {
    private static ExtentReports extent;

    private ExtentManager() {}

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String outDir = "target/extent-report";
            new File(outDir).mkdirs();

            ExtentSparkReporter spark = new ExtentSparkReporter(outDir + "/ExtentReport.html");
            spark.config().setReportName("Swagger Petstore API Automation Report");
            spark.config().setDocumentTitle("API Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Framework", "REST Assured + TestNG");
            extent.setSystemInfo("Base URL", System.getProperty("baseUrl", "https://petstore3.swagger.io/api/v3"));
        }
        return extent;
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
