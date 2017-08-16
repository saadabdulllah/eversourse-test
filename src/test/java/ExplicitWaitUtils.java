import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by saadabdullah on 8/15/17.
 */
public class ExplicitWaitUtils {
    WebDriver driver = null;
    private static final int DEFAULT_WAIT_TIME = 5000;

    public ExplicitWaitUtils(WebDriver driver){
        this.driver = driver;
    }


    public WebElement waitForElement(final By locator) {
        return waitForElement(locator,5000);
    }
    public WebElement waitForElement(final By locator, int timeToWaitInSec) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return foo;
    }
    public WebElement waitForElementDisplayed(final By locator) {
        return waitForElementDisplayed(locator,5000);
    }

    public WebElement waitForElementDisplayed(final By locator, int timeToWaitInSec) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element != null && element.isDisplayed()) {
                    //highlight(element);
                    return element;
                }
                return null;
            }
        });
        return foo;
    }


    /* public void waitForAjaxToComplete() {
         Wait<WebDriver> wait = fluentWait();
         wait.until(jQuryHasFinishedProcessing());
     }
 */
    public Wait<WebDriver> fluentWait() {
        return new FluentWait<WebDriver>(driver)
                .withTimeout(DEFAULT_WAIT_TIME, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoreAll(new ArrayList<Class<? extends Throwable>>() {
                    {
                        add(StaleElementReferenceException.class);
                    }
                }).withMessage("The message you will see in if a TimeoutException is thrown");
    }

    public FluentWait<WebDriver> fluentWait(int duration, TimeUnit timeUnit) {
        return new FluentWait<WebDriver>(driver)       //<3>
                .withTimeout(duration, timeUnit)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoreAll(new ArrayList<Class<? extends Throwable>>() {
                    {
                        add(StaleElementReferenceException.class);
                        add(NoSuchElementException.class);
                    }
                });
    }





}
