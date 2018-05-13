package com.xometry.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
  public static final String CHROME = "chrome";
  public static final String IE = "ie";
  public static final String FIREFOX = "firefox";

  public static WebDriver getDriver(String browser) {
    WebDriver driver = null;
    if (CHROME.equals(browser)) {
      driver = new ChromeDriver();
    } else if (FIREFOX.equals(browser)) {
      driver = new FirefoxDriver();
    } else if (IE.equals(browser)) {
      driver = new InternetExplorerDriver();
    }
    return driver;
  }
}
