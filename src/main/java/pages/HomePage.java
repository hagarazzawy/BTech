package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Duration timeout;

    private By searchBox = By.id("search");

    public HomePage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public ResultsPage search(String searchName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(searchName + Keys.ENTER);
        return new ResultsPage(driver, timeout);
    }
}
