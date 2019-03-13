package ru.stqa.training.selenium.PageObject.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void getCartPage() {
        driver.findElement(By.cssSelector("div#cart a[class=content]")).click();
    }

    public void removeProductFromCart() {
        WebElement tableElement = driver.findElement(By.cssSelector("table.dataTable.rounded-corners")) ;
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        wait.until(ExpectedConditions.stalenessOf(tableElement));
    }

    public void checkThatCartIsEmpty() {
        Assert.assertEquals("Cart is not empty", "There are no items in your cart.", driver.findElement(By
                .cssSelector("div#content em")).getAttribute("textContent"));
    }

    public int getNumberOfProducts() {
        return Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity"))
                .getAttribute("textContent"));
    }

    public void returnToPreviousPage() {
        driver.navigate().back();
    }
}
