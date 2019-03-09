package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CheckInNewWindows extends TestBase {

    @Test
    public void clickOnMenu() {

        adminLogin();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(By.linkText("Afghanistan")).click();
        List<WebElement> externalReferences = driver.findElements(By.cssSelector("i[class*=external-link"));
        String currentWindowHandle = driver.getWindowHandle();
        for(WebElement reference : externalReferences) {
            reference.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windowHandles = driver.getWindowHandles();
            driver.switchTo().window(getAnotherWindowHandle(windowHandles, currentWindowHandle));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));
            driver.close();
            driver.switchTo().window(currentWindowHandle);
        }
    }

    public String getAnotherWindowHandle(Set<String> handles, String unexpectedHandle) {
        String anotherHandle = null;
        Iterator<String> handleIterator = handles.iterator();
        while(handleIterator.hasNext()) {
            String handle = handleIterator.next();
            if(!unexpectedHandle.equals(handle)) {
                anotherHandle = handle;
            }
        }
        return anotherHandle;
    }
}