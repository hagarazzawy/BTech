package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class CartPage extends BasePage{
    private By productsInCart = By.xpath("//tr/td//div[contains(@class,'product-item-details')]//a");

    public CartPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public boolean isProductInCart(String productURL) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productsInCart));
        for (WebElement element : driver.findElements(productsInCart)) {
            String href = element.getAttribute("href");
            if (href.contains(productURL)) {
                return true;
            }
        }
        return false;
    }

}
