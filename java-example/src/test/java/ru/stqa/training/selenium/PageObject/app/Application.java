package ru.stqa.training.selenium.PageObject.app;

import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.PageObject.pages.CartPage;
import ru.stqa.training.selenium.PageObject.pages.MainPage;
import ru.stqa.training.selenium.PageObject.pages.ProductPage;

import java.util.concurrent.TimeUnit;

public class Application {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected MainPage mainPage;
    protected ProductPage productPage;
    protected CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void addProductsToCart(int productNumber) {
        Preconditions.checkArgument(productNumber > 0, "At least 1 product should be stated as parameter");

        mainPage.getMainPage();
        for(int product = 0; product < productNumber; product++) {
            mainPage.getRandomMostPopularProduct();
            productPage.addProductToCart();
            productPage.returnToPreviousPage();
        }
    }

    public void removeProductsFromCart(int productNumber) {
        Preconditions.checkArgument(productNumber > 0, "At least 1 product should be stated as parameter");

        mainPage.getMainPage();
        int initialProductNumber = cartPage.getNumberOfProducts();
        cartPage.getCartPage();
        for(int product = 0; product < productNumber; product++) {
            cartPage.removeProductFromCart();
        }

        if(initialProductNumber == productNumber) {
            cartPage.checkThatCartIsEmpty();
        }
    }

    public void quit() {
        driver.quit();
        driver = null;
    }
}