package com.prasaddys.hook;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.prasaddys.config.environment.EnvironmentReader;
import com.prasaddys.config.platforms.PlatformReader;
import com.prasaddys.interfaces.Platform;
import com.prasaddys.util.CommonConstants;
import com.prasaddys.util.JsonUtils;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.open;

public class CucumberHooks {

    private final Logger log = LogManager.getLogger(CucumberHooks.class);

//    @BeforeAll
//    public void beforeAll() {
//
//    }
//
//    @AfterAll
//    public void afterAll() {
//
//    }

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("Starting the scenario: {}" , scenario);
        Optional<Platform> platform = new PlatformReader().read().stream().findFirst();
        if (platform.isPresent()) {
            Platform currentPlatformType = platform.get();
            initializeBrowser(currentPlatformType);
            Configuration.fastSetValue = true;
            String envUrl = new EnvironmentReader(Optional.ofNullable(System.getProperty("env")).orElse("test")).getEnvUrl();
            open(envUrl);
            WebDriverRunner.getWebDriver().manage().window().maximize();
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        log.info("Ending the scenario: {}" , scenario);
        Selenide.closeWebDriver();
        log.info("Closing the browser.");
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("{} failed, capturing & attaching screenshot to report as base64.", scenario.getName());
            final byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image.png", scenario.getName());
        }
    }

    private void initializeBrowser(Platform platform) {
        // Get desired capabilities and remote URL from the platform
        DesiredCapabilities capabilities = platform.getDesiredCapabilities();
        URL remoteUrl = platform.getRemoteUrl();

        // Handle Mobile platform (for Android and iOS)
        if (platform.getPlatformType() == enums.PlatformType.MOBILE) {
            String os = platform.getOsName();
            switch (os.toLowerCase()) {
                case "android":
                    if (remoteUrl != null) {
                        Configuration.remote = remoteUrl.toString();
                        // Selenide will handle the AndroidDriver internally
                    }
                    Configuration.browser = "android";
                    log.info("Launching the {} browser.", os.toLowerCase());
                    break;
                case "ios":
                    if (remoteUrl != null) {
                        Configuration.remote = remoteUrl.toString();
                        // Selenide will handle the IOSDriver internally
                    }
                    Configuration.browser = "ios";
                    log.info("Launching the {} browser.", os.toLowerCase());
                    break;
                default:
                    log.error("Unsupported mobile OS: {}", os, new IllegalArgumentException("Unsupported mobile OS"));
            }
        }
        // Handle Web platform (for browsers)
        else if (platform.getPlatformType() == enums.PlatformType.WEB) {
            String browser = platform.getBrowserName();
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (capabilities != null) {
                        chromeOptions.merge(capabilities);
                    }
                    if (remoteUrl != null) {
                        Configuration.remote = remoteUrl.toString();
                        WebDriverRunner.setWebDriver(new RemoteWebDriver(remoteUrl, chromeOptions));
                    } else {
                        WebDriverManager.chromedriver().setup();
                        Configuration.browser = "chrome";
                        Configuration.browserCapabilities = chromeOptions;
                    }
                    log.info("Launching the {} browser.", browser.toLowerCase());
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (capabilities != null) {
                        firefoxOptions.merge(capabilities);
                    }
                    if (remoteUrl != null) {
                        Configuration.remote = remoteUrl.toString();
                        WebDriverRunner.setWebDriver(new RemoteWebDriver(remoteUrl, firefoxOptions));
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        Configuration.browser = "firefox";
                        Configuration.browserCapabilities = firefoxOptions;
                    }
                    log.info("Launching the {} browser.", browser.toLowerCase());
                    break;
                case "safari":
                    SafariOptions safariOptions = new SafariOptions();
                    if (capabilities != null) {
                        safariOptions.merge(capabilities);
                    }
                    if (remoteUrl != null) {
                        Configuration.remote = remoteUrl.toString();
                        WebDriverRunner.setWebDriver(new RemoteWebDriver(remoteUrl, safariOptions));
                    } else {
                        WebDriverManager.safaridriver().setup();
                        Configuration.browser = "safari";
                        Configuration.browserCapabilities = safariOptions;
                    }
                    log.info("Launching the {} browser.", browser.toLowerCase());
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (capabilities != null) {
                        edgeOptions.merge(capabilities);
                    }
                    if (remoteUrl != null) {
                        Configuration.remote = remoteUrl.toString();
                        WebDriverRunner.setWebDriver(new RemoteWebDriver(remoteUrl, edgeOptions));
                    } else {
                        WebDriverManager.edgedriver().setup();
                        Configuration.browser = "edge";
                        Configuration.browserCapabilities = edgeOptions;
                    }
                    log.info("Launching the {} browser.", browser.toLowerCase());
                    break;
                default:
                    log.error("Unsupported browser: {}", browser, new IllegalArgumentException("Unsupported browser"));
            }
        }
    }

    @DataProvider(name = "DataByMethod")
    public JSONObject[] fetchDataByMethod(Method method) {
        log.info("testcaseName: {}", method.getName());
        return getDataByMethod(method.getName());
    }

    @DataProvider(name = "DataByClass")
    public JSONObject[] fetchDataByClass() {
        return getDataByClass();
    }

    private JSONObject[] getDataByMethod(String methodName) {
        return JsonUtils.fetchData(CommonConstants.INPUT_DATA + "DataByMethod.json", methodName);
    }

    private JSONObject[] getDataByClass() {
        return JsonUtils.fetchData(CommonConstants.INPUT_DATA +  "DataByMethod.json", getClass().getSimpleName());
    }

}
