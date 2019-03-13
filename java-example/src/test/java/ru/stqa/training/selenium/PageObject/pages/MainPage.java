package ru.stqa.training.selenium.PageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void getMainPage() {
        driver.get("http://litecart.stqa.ru/en/");
    }

    @FindBy
    private List<WebElement> mostPopularProducts;

    public void getRandomMostPopularProduct() {
        driver.findElement(By.cssSelector("div#box-most-popular li:nth-child(" + (1 + (int) Math.random()
                * mostPopularProducts.size()) + ")")).click();
    }
}
