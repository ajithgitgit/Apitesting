package Apitest.utils;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class KarateExtentReporter {

    static ExtentReports extent;
    static ExtentTest test;

    public static void generateReport(Results results) {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/karate-extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Ajith Palani");

        // Add each feature result
        results.getScenarioResults().forEach(sr -> {
            test = extent.createTest(sr.getScenario().getFeature().getName() + " - " + sr.getScenario().getName());
            if (sr.isFailed()) {
                test.fail(sr.getError());
            } else {
                test.pass("Passed");
            }
        });

        extent.flush();
    }
}
