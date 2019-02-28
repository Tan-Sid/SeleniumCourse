package ru.stqa.training.selenium;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TagName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.xml.sax.Locator;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MenuClick extends TestBase {

    TestBase testBase;


//    @Test
//    public void litecartLogin() {
//        driver.get("http://localhost/litecart/admin/");
//        driver.findElement(By.name("username")).sendKeys("admin");
//        driver.findElement(By.name("password")).sendKeys("admin");
//        driver.findElement(By.name("login")).click();
//        wait.until(titleIs("My Store"));
//    }

    @Test
    public void menuClick() {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

//        List<WebElement> items = driver.findElements(By.cssSelector("#app- a"));
//
//        int itemsCount = items.size();
//        System.out.println(itemsCount);
//        System.out.println(items);
//        System.out.println(items.get(1));
//            for (int i = 0; i <= itemsCount-1; i++){
//
////                List<WebElement> items = driver.findElements(By.cssSelector("#app- > a"));
////                int itemsCount = items.size();
//                WebElement singleItem = items.get(i);
//                System.out.println(singleItem);
//                singleItem.click();
//////                testBase = testBase.isElementPresent(By.tagName("h1"));
//
//            }
//        foreach (var item )

        int i = 0;
            while (true) {
                List<WebElement> items = driver.findElements(By.cssSelector("#app- a"));
                if (i >= items.size()) {
                    break;
                }
                items.get(i++).click();
            }



    }

}
