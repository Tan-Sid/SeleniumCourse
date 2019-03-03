package ru.stqa.training.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductPageVerify extends TestBase {

    @Test
    public void openRightProductPage() {

        driver.get("http://localhost/litecart/");

        WebElement product = driver.findElement(By.xpath("//*[@id='box-campaigns']//li[1]"));
        String nameHome = product.findElement(By.cssSelector("div.name")).getText();

        WebElement rPriceHome = product.findElement(By.cssSelector(".regular-price"));
        String regularPriceHome = rPriceHome.getText();

        WebElement cPriceHome = product.findElement(By.cssSelector(".campaign-price"));
        String campaignPriceHome = cPriceHome.getText();

        checkPriceStyle(rPriceHome, cPriceHome);

        driver.findElement(By.xpath("//*[@id='box-campaigns']//li[1]")).click();
        String name = driver.findElement(By.cssSelector("#box-product h1")).getText();
        Assert.assertTrue(nameHome.equals(name));

        WebElement rPrice = driver.findElement(By.cssSelector(".regular-price"));
        String regularPrice = rPrice.getText();

        WebElement cPrice = driver.findElement(By.cssSelector(".campaign-price"));
        String campaignPrice = cPrice.getText();

        Assert.assertTrue(regularPriceHome.equals(regularPrice));
        Assert.assertTrue(campaignPriceHome.equals(campaignPrice));

        checkPriceStyle(rPrice, cPrice);

    }

    private void checkPriceStyle(WebElement rPrice, WebElement cPrice) {
        Assert.assertEquals(rPrice.getTagName(), "s");
        ArrayList<String> rP = priceColorToList(rPrice);
        Assert.assertTrue(rP.get(0).equals(rP.get(1)) && rP.get(1).equals(rP.get(2)));

        Assert.assertEquals(cPrice.getTagName(), "strong");
        ArrayList<String> cP = priceColorToList(cPrice);
        Assert.assertTrue(!(cP.get(0).equals("0")) && cP.get(1).equals("0") && cP.get(2).equals("0"));

        float rPS = Float.parseFloat(rPrice.getCssValue("font-size").replace("px", ""));
        float cPS = Float.parseFloat(cPrice.getCssValue("font-size").replace("px", ""));
        Assert.assertTrue(rPS < cPS);
    }

    private ArrayList<String> priceColorToList(WebElement price) {
        String pColor = price.getCssValue("color");
        ArrayList<String> p = new ArrayList<>();
        p.addAll(Arrays.asList(price.getCssValue("color").substring(pColor.indexOf("(") + 1, pColor.indexOf(")")).replace(" ", "").split(",")));

        return p;
    }
}