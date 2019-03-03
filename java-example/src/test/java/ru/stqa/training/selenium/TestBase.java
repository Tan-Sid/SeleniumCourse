package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

  public WebDriver driver;
  public WebDriverWait wait;
  public By by;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver,10);
  }

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
