package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;

public class ResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Duration timeout;

    private By firstResult = By.xpath("//div[contains(@class,'products list')]/div[contains(@class,'product-item-view')][1]");
    public ResultsPage(WebDriver driver, Duration timeout) {
        this.wait = new WebDriverWait(driver, timeout);
        this.driver = driver;
    }

    public ProductPage selectFirstResult() {
        driver.findElement(firstResult).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstResult);

        return new ProductPage(driver, timeout);
    }
}

