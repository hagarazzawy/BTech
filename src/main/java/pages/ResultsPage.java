package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

public class ResultsPage extends BasePage{
    private By firstResultLink = By.xpath("//*[@id='product_view_0']/a");
    private By clearFilterButton = By.cssSelector("#layered-filter-block #vshop_filter .clear_price");

    public ResultsPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public void selectFirstResult() {
        wait.until(ExpectedConditions.elementToBeClickable(clearFilterButton));
        WebElement firstResult = driver.findElement(firstResultLink);
        clickJS(firstResult);
    }
}

