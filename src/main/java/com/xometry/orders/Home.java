package com.xometry.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
  WebDriver driver;

  @FindBy(linkText = "Log In")
  WebElement loginLink;

  public Home(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void clickLoginLink() {
    this.loginLink.click();
  }
}
