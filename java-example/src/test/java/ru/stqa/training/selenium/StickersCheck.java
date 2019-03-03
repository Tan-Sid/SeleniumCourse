package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StickersCheck extends TestBase {

    @Test
    public void checkStickersOnMainPage() {
        driver.get("http://localhost/litecart/");

        List<WebElement> elements = driver.findElements(By.cssSelector("li.product"));
        for (int i = 0; i < elements.size(); i++) {
            int numberOfStickers = elements.get(i).findElements(By.cssSelector("[class^=sticker]")).size();
            Assert.assertEquals("Stickers: " + numberOfStickers,
                    1, numberOfStickers);
        }
    }
}