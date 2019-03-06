package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class CheckInNewWindows extends TestBase {

    @Test
    public void clickOnMenu() {

        adminLogin();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(By.linkText("Afghanistan")).click();
        List<WebElement> allLinks = driver.findElements(By.cssSelector("a>i.fa.fa-external-link"));
        String handle = driver.getWindowHandle();
        Set<String> newHandles;
        for (int i = 0; i < allLinks.size(); i++) {
            allLinks.get(i).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));
            newHandles = driver.getWindowHandles();
            for (String a : newHandles) {
                if (!a.equals(handle)) {
                    driver.switchTo().window(a);
                    driver.close();
                    driver.switchTo().window(handle);
                }
            }
        }
    }
}