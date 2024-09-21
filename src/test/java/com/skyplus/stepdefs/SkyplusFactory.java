package com.skyplus.stepdefs;

import com.skyplus.generic.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SkyplusFactory
{

    private final ConfigReader configReader;
    private final Properties prop;
    private final Logger log = LogManager.getLogger(SkyplusFactory.class);
    private final boolean run_on_browserstack;
    private final String browser_to_run;
    private final String env_to_run;
    protected String url;
    private WebDriver driver;
    private ChromeOptions chromeOptions;
    private String browser;

    public SkyplusFactory(ConfigReader configReader) throws MalformedURLException
    {
        this.configReader = configReader;
        this.prop = this.configReader.init_prop();
        this.run_on_browserstack = Boolean.parseBoolean(System.getProperty("RUN_ON_BROWSERSTACK"));
        this.browser_to_run = System.getProperty("BROWSER_TO_USE");
        this.env_to_run = System.getProperty("RUN_ON_ENV");

    }

    private void initRemoteDriver() throws MalformedURLException
    {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "cucumber-java:sample-sdk:v1.2.4");
        capabilities.setCapability("bstack:options", bstackOptions);
//        capabilities.setCapability("googlegeolocationaccess.enabled", false);
        driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);
    }

    @Before(order = 0)
    public void setUp() throws MalformedURLException
    {
        setEnv(env_to_run);
        log.info("Setting up the webdriver");
        if (run_on_browserstack)
        {
            log.info("Tests will be executed on Browserstack cloud");
            initRemoteDriver();
        }
        else
        {
            if (browser_to_run == null || browser_to_run.isEmpty())
            {
                log.info("Tests will be executed on local");
                browser = this.prop.getProperty("browser");
            }
            else
            {
                browser = browser_to_run;
            }
            log.info("browser value is: " + browser);
            switch (browser.toLowerCase())
            {
                case "chrome":
                    ChromeOptions optionc = new ChromeOptions();
                    optionc.addArguments("--remote-allow-origins=*");
                    optionc.setExperimentalOption("excludeSwitches", Arrays.asList(
                            "enable-automation"));
                    driver = new ChromeDriver(optionc);
                    driver.manage().window().maximize();
                    break;
                case "chrome-headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--headless");
                    options.addArguments("window-size=1200,1100");
                    driver = new ChromeDriver(options);
                    driver.manage().window().setSize(new Dimension(1200,1100));
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;

                case "edge":
                      EdgeOptions optionse = new EdgeOptions();
                      optionse.addArguments("--remote-allow-origins=*");
                       optionse.setExperimentalOption("excludeSwitches", Arrays.asList(
                            "enable-automation"));
                       driver = new EdgeDriver(optionse);
                    break;
                case "mobile":
                    setUpDevice();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                default:
                    driver = new ChromeDriver();
                    break;
            }

            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
        driver.manage().window().maximize();
    }

    /**
     * This method is used to setup the device emulator
     */
    public void setUpDevice()
    // https://chromedriver.chromium.org/mobile-emulation - refer this for device
    // name list
    {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", prop.getProperty("device_Name"));
        chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

    }





    @After(order = 1)
    public void tearDown(Scenario scenario)
    {
        log.info("Taking the screnshot");
        if (scenario.isFailed())
        {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);

        }
    }

    @After(order = 0)
    public void quitBrowser()
    {
        log.info("Quitting the webdriver");
        if (driver == null)
        {
            return;
        }

        driver.quit();

    }


    public WebDriver getDriver()
    {

        return driver;
    }

    public Properties getProp()
    {

        return prop;
    }

    public void setEnv(String env)
    {
        if (env == null || env.isEmpty())
        {
            this.url = getProp().getProperty("dev");

        }
        else
        {
            if (env.equalsIgnoreCase("dev"))
            {

                this.url = getProp().getProperty("dev");
            }
            else if (env.equalsIgnoreCase("qa"))
            {

                this.url = getProp().getProperty("qa");
            }
            else if (env.equalsIgnoreCase("pre-prod"))
            {

                this.url = getProp().getProperty("pre-prod");
            }
            else if (env.equalsIgnoreCase("prod"))
            {
                this.url = getProp().getProperty("prod");
            }
        }

    }
}