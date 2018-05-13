package com.xometry.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
  WebDriver driver;

  @FindBy(id = "email_address")
  WebElement emailAddressElement;

  @FindBy(id = "password")
  WebElement passwordElement;

  @FindBy(id = "login-btn")
  WebElement loginBtnElement;

  public Login(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void clickLogin(String emailAddress, String password) {
    Util.waitForElementToBeClickable(driver, emailAddressElement);
    emailAddressElement.sendKeys(emailAddress);
    passwordElement.sendKeys(password);
    loginBtnElement.click();
  }
}
