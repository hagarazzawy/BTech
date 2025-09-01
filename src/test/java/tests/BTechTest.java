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
        logger.info("Opening URL " + url);
        homePage = new HomePage(driver, timeout);
        resultsPage = new ResultsPage(driver, timeout);
        productPage = new ProductPage(driver, timeout);
        cartPage = new CartPage(driver, timeout);
    }

    @Description("Search for iPhone 16, validate first result has image, add it to cart, and navigate to cart page")
    @Story("Add first search result to cart")
    @Test(description = "Search and Add iPhone 16 to Cart")
    public void addFirstSearchResultToCart() {
        homePage.search(config.getProperty("searchProduct"));
        resultsPage.selectFirstResult();
        logger.info("First search result selected");
        Assert.assertTrue(productPage.isProductWithImage(), "product doesn't have an image");
        logger.info("First search result has image");
        String productURL = productPage.getProductURL();
        productPage.addToCart();
        logger.info("Add to cart clicked");
        productPage.goToCartFromPopup();
        logger.info("Go to cart page clicked");
        Assert.assertTrue(cartPage.isProductInCart(productURL), "product is not in the cart");
        logger.info("Product added to cart");

    }
}
