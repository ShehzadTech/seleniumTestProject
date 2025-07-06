package stepdefs;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addToCart{

    WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com";

    private WebElement waitUntilVisible(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(driver -> driver.findElement(locator));
    }
    private void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Given("User is logged in")
    public void userIsLoggedIn() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        // Wait until inventory.html page loads
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver->webDriver.getCurrentUrl().contains("/inventory.html"));
    }

    @When("User adds the product to the cart")
    public void userAddsTheProductToTheCart() {
        String productName = "Sauce Labs Backpack";
        WebElement addBtn= waitUntilVisible(By.id("add-to-cart-sauce-labs-backpack"));
        addBtn.click();

    }

    @Then("the cart badge should display {string}")
    public void theCartBadgeShouldDisplay(String expectedCount) {
        WebElement cartCount=waitUntilVisible(By.className("shopping_cart_badge"));
       // assertEquals(expectedCount, cartCount.getText().trim(), "Cart doesn't contain the expected number of products");
        try {
            assertEquals(expectedCount, cartCount.getText().trim(), "Cart doesn't contain the expected number of products");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }


    @And("the cart should contain {string}")
    public void theCartShouldContain(String productName) {
       WebElement cart= driver.findElement(By.className("shopping_cart_link"));
       cart.click();
        WebElement product = waitUntilVisible(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"));
        assertTrue(product.isDisplayed(), "Product not found in cart: " + productName);
        closeDriver();

    }
}
