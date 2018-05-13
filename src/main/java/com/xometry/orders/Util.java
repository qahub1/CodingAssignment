package com.xometry.orders;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;

public class Util {

  public static JSONArray getJSONFile(File file) throws Exception {
    JSONParser parser = new JSONParser();
    return (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));
  }

  public static void letPageLoad(WebDriver driver) {
    NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
    ngWebDriver.waitForAngularRequestsToFinish();
  }

  public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public static String getLabelContainsText(String text) {
    StringBuilder label = new StringBuilder("//label[contains(text(), '");
    label.append(text).append("')]");
    return label.toString();
  }
}
