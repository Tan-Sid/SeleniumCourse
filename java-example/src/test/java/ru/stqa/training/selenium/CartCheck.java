package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CartCheck extends TestBase {

    @Test
    public void checkCart() {

        driver.get("http://litecart.stqa.ru/en/");

        List<WebElement> products;
        for(int product = 0; product < 3; product++) {
            products = driver.findElements(By.cssSelector("div#box-most-popular li"));
            products.get(product).click();

            if(isElementPresent(driver, By.cssSelector("td.options select"))) {
                Select options = new Select(driver.findElement(By.cssSelector("select[name*=options]")));
                options.selectByIndex(1);
            }

            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"),
                    Integer.valueOf(product + 1).toString()));

            driver.navigate().back();
        }

        driver.findElement(By.cssSelector("div#cart a[class=content]")).click();

        for(int product = 0; product < 3; product++) {
            WebElement tableElement = driver.findElement(By.cssSelector("table.dataTable.rounded-corners")) ;
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
            wait.until(ExpectedConditions.stalenessOf(tableElement));
        }
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#content em"),
                "There are no items in your cart."));
    }
}