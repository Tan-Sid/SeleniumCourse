package ru.stqa.training.selenium.PageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void returnToPreviousPage() {
        driver.navigate().back();
    }

    public void addProductToCart() {
        Integer initialProductsInCart = Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity"))
                .getAttribute("textContent"));
        if (isElementPresent(driver, By.cssSelector("td.options select"))) {
            Select options = new Select(driver.findElement(By.cssSelector("select[name*=options]")));
            options.selectByIndex(1);
        }

        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"),
                Integer.valueOf(initialProductsInCart + 1).toString()));
    }
}
