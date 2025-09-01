package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;
import java.time.Duration;

import org.testng.ITestResult;

public class BaseTest {
    protected WebDriver driver;
    protected Properties config;
    protected Duration timeout;
    protected String url;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);


    @BeforeClass
    public void setup() throws Exception {
        logger.info("Loading configuration file...");
        config = new Properties();
        config.load(new FileInputStream("src/main/resources/config.properties"));

        timeout = Duration.ofSeconds(Long.parseLong(config.getProperty("timeout", "10")));

        url = config.getProperty("url");

        String browser = config.getProperty("browser", "chrome").toLowerCase();
        logger.info("Opening browser: " + browser);
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(timeout);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void logTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        logger.info("Starting test: " + className + "." + testName);
    }

    @AfterMethod
    public void logTestResult(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                logger.info("Test " + className + "." + testName + " passed ");
                break;
            case ITestResult.FAILURE:
                logger.error("Test " + className + "." + testName + " failed");
                break;
        }
    }

    @AfterClass
    public void tearDown() {
        logger.info("Closing browser");
        driver.quit();

    }
}

