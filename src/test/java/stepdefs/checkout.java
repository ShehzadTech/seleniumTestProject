 package stepdefs;

import Pages.CheckoutPage;
import Pages.LoginPage;
import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
        import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class checkout {

    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage;
    CheckoutPage checkoutPage;

    @Given("I have {string} in my cart")
    public void i_have_product_in_cart(String productName) {
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        checkoutPage = new CheckoutPage(driver);
        checkoutPage.addProductToCart(productName);
        checkoutPage.goToCart();
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        checkoutPage.clickCheckout();
    }

    @When("I fill in the checkout form with:")
    public void i_fill_in_the_checkout_form_with(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        driver.wait(1000);
        String firstName = data.get(0).get("firstName");
        String lastName = data.get(0).get("lastName");
        String postalCode = data.get(0).get("postalCode");

        checkoutPage.fillCheckoutForm(firstName, lastName, postalCode);
    }

    @When("I finish the checkout process")
    public void i_finish_the_checkout_process() {
        checkoutPage.finishCheckout();
    }

    @Then("I should see the confirmation message {string}")
    public void i_should_see_the_confirmation_message(String expectedMessage) {
        String actualMessage = checkoutPage.getConfirmationMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Confirmation message mismatch!");
    }
}
