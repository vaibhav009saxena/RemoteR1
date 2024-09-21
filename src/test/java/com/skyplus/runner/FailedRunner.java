package com.skyplus.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        glue = {"com.skyplus.stepdefs"}, // path of step definition

        plugin = {"pretty",
                "rerun:target/failedrerun.txt",
                "html:reports/tests/cucumber/cucumber-pretty.html",
                "testng:reports/tests/cucumber/testng/cucumber.xml",
                "json:reports/tests/cucumber/json/cucumberTestReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",},
        monochrome = true,
        features = {"@target/failedrerun.txt"}

)

public class FailedRunner extends AbstractTestNGCucumberTests {
}