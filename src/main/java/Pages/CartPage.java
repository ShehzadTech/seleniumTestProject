package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;



public class CartPage {

    private final WebDriver driver;

    private final By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement waitUntilVisible(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(driver -> driver.findElement(locator));
    }

    public void addBackpackToCart() {
        waitUntilVisible(addBackpackBtn).click();
    }

    public void verifyCartCount(String expectedCount) {
        String actual = waitUntilVisible(cartBadge).getText().trim();
        assertEquals(actual, expectedCount, "Cart count mismatch!");
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public void verifyProductInCart(String productName) {
        WebElement product = waitUntilVisible(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"));
        assertTrue(product.isDisplayed(), "Product not found in cart: " + productName);
    }
}
