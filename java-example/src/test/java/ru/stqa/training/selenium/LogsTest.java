package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

public class LogsTest extends TestBase {

    @Test
    public void checkLogging() {
        adminLogin();

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> elements = driver.findElements(By.cssSelector("tr.row td > a[href*=product]:not([title=Edit])"));

        for(int index = 0; index < elements.size(); index++) {
            elements = driver.findElements(By.cssSelector("tr.row td > a[href*=product]:not([title=Edit])")) ;
            elements.get(index).click();
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                String logErrorLevel = l.getLevel().toString();
                Assert.assertEquals("There is an error on page with " + logErrorLevel + " level", logErrorLevel, null);
            }
                driver.navigate().back();
        }
    }
}
