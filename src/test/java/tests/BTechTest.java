package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import pages.*;

public class BTechTest extends BaseTest {

    private HomePage homePage;
    private ResultsPage resultsPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUpPages() {
        driver.get(url);
        logger.info("Opened BTech website");
        homePage = new HomePage(driver, timeout);
        resultsPage = new ResultsPage(driver, timeout);
        productPage = new ProductPage(driver, timeout);
        cartPage = new CartPage(driver, timeout);
    }
    @Description("Search for iPhone 16, validate first result has image, add it to cart, and navigate to cart page")
    @Test(description = "Search and Add iPhone 16 to Cart")
    public void addProductToCart() {
        homePage.search(config.getProperty("searchProduct"));
        resultsPage.selectFirstResult();
        logger.info("First search result selected");
        Assert.assertTrue(productPage.isProductWithImage(), "product doesn't have an image");
        logger.info("First search result contains image");

        productPage.isProductWithImage();
        productPage.addToCart();
        productPage.goToCartFromPopup();
        Assert.assertTrue(cartPage.isProductInCart(config.getProperty("searchProduct")), "product is not in the cart");
        logger.info("Product added to cart");

    }
}
