package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TestBaseNightly {

  public WebDriver driver;
  public WebDriverWait wait;

  @Before
  public void start() {
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
    driver = new FirefoxDriver(options);
    wait = new WebDriverWait(driver,10);
  }

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
