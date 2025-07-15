
package stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtility;
import utils.TestDataReader;
import Hooks.Hooks;

import java.time.Duration;

import static org.testng.Assert.*;

public class Login {

    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage;

    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @When("I login with username and password")
    public void loginWithValidCreds() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("I should be redirected to the inventory page")
    public void verifyInventoryPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> driver.getCurrentUrl().contains("/inventory.html"));
        assertTrue(driver.getCurrentUrl().contains("inventory"), "User not on inventory page");
    }

    @When("I login with locked user credentials")
    public void loginWithLockedUser() {
        loginPage.login("locked_out_user", "secret_sauce");
    }

    @Then("I should see an error message")
    public void verifyLockedErrorMessage() {
        String errorText = loginPage.getErrorMessage();
        try {
            ScreenshotUtility.captureScreenshot(driver, "before_assertion");
            assertTrue(errorText.contains("locked"), "Expected locked user error message");
        } catch (AssertionError e) {
            ScreenshotUtility.captureScreenshot(driver, "login_error_failed_assertion");
            throw e;
        }
    }

    @When("I login with random credentials")
    public void loginWithRandomCredentials() {
        String fakeUsername = TestDataReader.getFakeUsername();
        String fakePassword = TestDataReader.getFakePassword();
        System.out.println("Trying with fake username: " + fakeUsername + " and password: " + fakePassword);
        loginPage.login(fakeUsername, fakePassword);
    }

    @Then("I should see an error message {string}")
    public void verifyErrorMessageWithParameter(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        assertTrue(actualError.contains(expectedError), "Error message mismatch");
    }

    @When("I login with username and wrong password")
    public void loginWithWrongPassword() {
        loginPage.login("shehzad", "wrong_password");
    }

    @When("I login with credentials from config")
    public void loginWithConfig() {
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);
    }
}

