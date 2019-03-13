package ru.stqa.training.selenium.PageObject.tests;

import org.junit.Test;
import ru.stqa.training.selenium.PageObject.app.Application;

public class CartTest {
    final static int PRODUCT_NUMBER_FOR_TEST = 3;

    @Test
    public void cartTest() {
        Application application = new Application();
        application.addProductsToCart(PRODUCT_NUMBER_FOR_TEST);
        application.removeProductsFromCart(PRODUCT_NUMBER_FOR_TEST);
        application.quit();
    }
}