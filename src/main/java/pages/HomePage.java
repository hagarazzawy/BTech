package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private Duration timeout;

    private By searchBox = By.id("search");
    private By dailyDeals = By.cssSelector(".daily-deal-wrapper #Daily-deals");

    public HomePage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public void search(String searchName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dailyDeals));
        driver.findElement(searchBox).sendKeys(searchName + Keys.ENTER);
    }
}
