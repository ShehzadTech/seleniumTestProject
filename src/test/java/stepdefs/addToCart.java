package stepdefs;

import config.Methods;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.CartPage;
import Pages.LoginPage;


import java.time.Duration;
import Hooks.Hooks;
public class addToCart {

    WebDriver driver;
    LoginPage loginPage;
    CartPage cartPage;

    @Given("User is logged in")
    public void userIsLoggedIn() {
        WebDriver driver = Hooks.getDriver();


        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> driver.getCurrentUrl().contains("/inventory.html"));
    }

    @When("User adds the product to the cart")
    public void userAddsProductToCart() {
        cartPage.addBackpackToCart();
    }

    @Then("the cart badge should display {string}")
    public void cartBadgeShouldDisplay(String expectedCount) {
        cartPage.verifyCartCount(expectedCount);
    }

    @And("the cart should contain {string}")
    public void cartShouldContainProduct(String productName) {
        cartPage.openCart();
        cartPage.verifyProductInCart(productName);

    }



}
