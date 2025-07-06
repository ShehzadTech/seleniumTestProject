package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.*;


public class Login {
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



    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @When("I login with username and password")
    public void loginWithUsernameAndPassword() {
        //driver.findElement(By.id("user-name")).sendKeys("standard_user");
        WebElement usernameField = waitUntilVisible(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginBtn.click();
    }
// if assertion true then test passes otherwise shows message
    @Then("I should be redirected to the inventory page")
    public void shouldBeRedirectedToTheInventoryPage() {
    // Wait until inventory.html page loads
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> webDriver.getCurrentUrl().contains("/inventory.html"));
        //Verify if the page contains label inventory to make sure page loaded
        assertTrue(driver.getCurrentUrl().contains("inventory"), "User not on inventory page");
        closeDriver();

    }


    @When("I login with locked user credentials")

    public void EnterUsernameAndPassword() {
        openLoginPage();
        WebElement usernameField = waitUntilVisible(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        usernameField.sendKeys("locked_out_user");
        passwordField.sendKeys("secret_sauce");
        loginBtn.click();
    }



    @Then("I should see an error message")
    public void ShouldSeeErrorMessage() {
        WebElement errorElement = waitUntilVisible(By.cssSelector("[data-test='error']"));
        String errorText = errorElement.getText().trim();
        assertTrue(errorText.contains("locked"));
        closeDriver();

    }

    @When("I login with username and wrong password")
    public void LoginWithUsernameAndWrongPassword() {
        openLoginPage();
        WebElement usernameField = waitUntilVisible(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        usernameField.sendKeys("error_user");
        passwordField.sendKeys("secret_sauce");
        loginBtn.click();

    }


    @Then("I should see an error message {string}")
    public void credentialsErrorMessage() {
        WebElement errorElement = waitUntilVisible(By.cssSelector("[data-test='error']"));
        String errorText = errorElement.getText().trim();
        assertTrue(errorText.contains("Username and password do not match any user in this service"));
        closeDriver();


    }
}