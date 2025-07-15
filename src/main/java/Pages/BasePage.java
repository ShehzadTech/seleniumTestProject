package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitUntilVisible(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(d -> d.findElement(locator));
    }

    protected void click(By locator) {
        waitUntilVisible(locator).click();
    }

    protected void type(By locator, String text) {
        waitUntilVisible(locator).clear();
        waitUntilVisible(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return waitUntilVisible(locator).getText().trim();
    }
}
