package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage{
    private By addToCartButton = By.id("product-addtocart-button");
    private By productImage = By.xpath("//div[@class=\"product media\"]/div[4]//img");
    private By goToCartButton = By.xpath("//button[contains(@class,'go-to-cart-btn')]");

    public ProductPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public boolean isProductWithImage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        WebElement image = driver.findElement(productImage);
        return image.isDisplayed();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    public void goToCartFromPopup() {
        wait.until(ExpectedConditions.presenceOfElementLocated(goToCartButton));
        driver.findElement(goToCartButton).click();
    }

    public String getProductURL()
    {
        return driver.getCurrentUrl();
    }
}

