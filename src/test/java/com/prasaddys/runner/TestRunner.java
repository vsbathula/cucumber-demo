package com.prasaddys.runner;


import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/prasaddys/hook", "com/prasaddys/stepdefinitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        publish = true
//        tags = "@Current"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass
    public void setReportInfo() {
        ExtentService.getInstance().setSystemInfo("App", "Prasaddys");
        ExtentService.getInstance().setSystemInfo("Cloud", System.getProperty("cloud"));
        ExtentService.getInstance().setSystemInfo("Mobile", System.getProperty("mobile"));
        ExtentService.getInstance().setSystemInfo("Environment", System.getProperty("env"));
        ExtentService.getInstance().setSystemInfo("Browser", System.getProperty("browser"));
        ExtentService.getInstance().setSystemInfo("Headless", System.getProperty("headless"));
        ExtentService.getInstance().setSystemInfo("OS", System.getProperty("os.name"));
        ExtentService.getInstance().setSystemInfo("OS.Version", System.getProperty("os.version"));
    }
}