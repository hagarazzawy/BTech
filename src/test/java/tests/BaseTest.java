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
        logger.info("Launching browser: " + browser);
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

    @AfterClass
    public void tearDown() {
        logger.info("Closing browser");
        driver.quit();

    }
}

