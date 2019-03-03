package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class MenuClick extends TestBase {

    @Test
    public void menuClick() {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menu = driver.findElements(By.cssSelector("li#app-"));
        for (int i = 0; i < menu.size(); i++) {
            menu.get(i).click();
            assertNotNull(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
            menu = driver.findElements(By.cssSelector("li#app-"));
            List<WebElement> items = menu.get(i).findElements(By.cssSelector("li"));
            for (int k = 0; k < items.size(); k++) {
                items.get(k).click();
                assertNotNull(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
                menu = driver.findElements(By.cssSelector("li#app-"));
                items = menu.get(i).findElements(By.cssSelector("li"));
            }
        }
    }
}
