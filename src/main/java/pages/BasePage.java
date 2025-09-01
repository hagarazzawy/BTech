package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;


public class BasePage {
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected WebDriverWait wait;
    protected Actions actions;


    public BasePage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, timeout);
        this.actions = new Actions(driver);
    }

    public void clickJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
    public void clickActions(WebElement element) {
        actions.moveToElement(element).click().perform();
    }
}
