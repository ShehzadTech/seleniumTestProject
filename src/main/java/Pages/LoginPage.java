//package Pages;
//
//import config.Methods;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.FluentWait;
//
//import java.time.Duration;
//import java.util.NoSuchElementException;
//public class LoginPage {
//
//    private final WebDriver driver;
//
//    // Locators
//    private final By username = By.id("user-name");
//    private final By password = By.id("password");
//    private final By loginBtn = By.id("login-button");
//    private final By errorMessage = By.cssSelector("[data-test='error']");
//
//    // Constructor
//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    // Navigate to login page
//    public void open() {
//        driver.get(Methods.BASE_URL);
//    }
//
//    // Login action
//    public void login(String user, String pass) {
//        waitUntilVisible(username).sendKeys(user);
//        driver.findElement(password).sendKeys(pass);
//        driver.findElement(loginBtn).click();
//    }
//
//    // Get error message with wait
//    public String getErrorMessage() {
//        return waitUntilVisible(errorMessage).getText().trim();
//    }
//
//    // Helper: Wait until element is visible
//    private WebElement waitUntilVisible(By locator) {
//        return new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofMillis(500))
//                .ignoring(NoSuchElementException.class)
//                .until(driver -> driver.findElement(locator));
//    }
//
//}

package Pages;

import config.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginPage {

    private final WebDriver driver;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(Methods.BASE_URL); // Make sure BASE_URL is set correctly in Methods.java
    }

    public void login(String user, String pass) {
        waitUntilVisible(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public String getErrorMessage() {
        return waitUntilVisible(errorMessage).getText().trim();
    }

    private WebElement waitUntilVisible(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(d -> d.findElement(locator));
    }
}

