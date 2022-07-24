package uiActions;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


import java.time.Duration;
import java.util.Set;

@Slf4j

public class GenericActions {
    WebDriver driver;
    public GenericActions(WebDriver driver){
        this.driver=driver;
    }

    public Wait<WebDriver> fluentWait() {

        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class).ignoring(Exception.class);

    }

    public void waitForElementAndThenClick(WebElement element) {
        Wait<WebDriver> waitFor = fluentWait();
        WebElement ele = waitFor.until(ExpectedConditions.elementToBeClickable(element));

        ele.click();
    }



    public void waitForElementAndUpdateText(WebElement element, String text) {

        Wait<WebDriver> waitFor = fluentWait();
        WebElement ele = waitFor.until(ExpectedConditions.elementToBeClickable(element));
        ele.clear();
        ele.sendKeys(text);
    }
    public void waitForBrowser(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {

            log.info("unable to locate element even after {} seconds",time);
        }
    }
    public void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public boolean isElementPresent(WebElement ele){
        boolean result= true;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        try{
            result= ele.getSize().width>0;
        }catch(NoSuchElementException e){
            result=false;
        }
        return result;
    }

    public String swithToNewTabCloseTheCurrentTab() {

        String currentWindow = driver.getWindowHandle();
        waitForBrowser(2);
        Set<String> tabs = driver.getWindowHandles();
        tabs.remove(currentWindow);
        driver.switchTo().window((String) tabs.toArray()[0]);
        return currentWindow;

    }
}
