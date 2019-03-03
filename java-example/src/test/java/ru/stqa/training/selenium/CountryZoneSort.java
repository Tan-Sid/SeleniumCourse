package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class CountryZoneSort extends TestBase {

    @Test
    public void sortCountriesAndZones() {

        adminLogin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<String> countriesNames = new ArrayList<String>();
        List<String> countriesWhithZones = new ArrayList<String>();
        for (WebElement countrie : driver.findElements(By.cssSelector(".row"))) {
            String name = countrie.findElement(By.xpath("./td[5]/a")).getText();
            countriesNames.add(name);
            if (countrie.findElement(By.xpath("./td[6]")).getText().equals("0")) continue;
            countriesWhithZones.add(name);
        }
        System.out.println("Список стран: " + countriesNames);
        System.out.println("Список зон: " + countriesWhithZones);
        checkSort(countriesNames);

        for (String name : countriesWhithZones) {
            driver.findElement(By.xpath("//a[.='" + name + "']")).click();
            Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), name);
            List<String> zonesNames = new ArrayList<String>();
            for (WebElement zone : driver.findElements(By.xpath("//*[@id='table-zones']//td[3]"))) {
                String zoneName = zone.findElement(By.cssSelector("input")).getAttribute("value");
                if (zoneName.equals("")) continue;
                zonesNames.add(zoneName);
            }
            checkSort(zonesNames);
            driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        }
    }

    @Test
    public void sortGeoZones() {
        adminLogin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<String> countriesNames = new ArrayList<String>();
        for (WebElement countrie : driver.findElements(By.cssSelector(".row"))) {
            countriesNames.add(countrie.findElement(By.xpath("./td[3]/a")).getText());
        }

        System.out.println("Список стран: " + countriesNames);
        for (String name : countriesNames) {
            System.out.println("Открываем страну: " + name);
            driver.findElement(By.xpath("//a[.='" + name + "']")).click();
            Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), name);
            List<String> zonesNames = new ArrayList<String>();
            for (WebElement zone : driver.findElements(By.xpath("//*[@id = 'table-zones']//td[3]//option"))) {
                if (zone.getAttribute("selected") != null)
                    zonesNames.add(zone.getText());
            }
            System.out.println("Список геозон: " + zonesNames);
            checkSort(zonesNames);
            driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }

    private void checkSort(List<String> list) {
        Assert.assertTrue(list.size() > 0);
        List<String> sortedList = new ArrayList<String>(list);
        Collections.sort(sortedList);
        Assert.assertEquals(sortedList, list);
    }
}