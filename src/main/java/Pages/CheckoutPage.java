package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import Pages.LoginPage;

public class CheckoutPage{

    private final WebDriver driver;

    // Locators
    private final By addToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By checkoutButton = By.id("checkout");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By confirmationHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement waitUntilVisible(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(d -> d.findElement(locator));
    }

    public void addProductToCart(String productName) {

        if (productName.equalsIgnoreCase("Sauce Labs Backpack")) {
            waitUntilVisible(addToCartBtn).click();
        }
    }

    public void goToCart() {
        waitUntilVisible(cartIcon).click();
    }

    public void clickCheckout() {
        waitUntilVisible(checkoutButton).click();
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        waitUntilVisible(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
        driver.findElement(continueBtn).click();
    }

    public void finishCheckout() {
        waitUntilVisible(finishBtn).click();
    }

    public String getConfirmationMessage() {
        return waitUntilVisible(confirmationHeader).getText().trim();
    }
}

