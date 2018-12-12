package pl.soflab.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

  @FindBy(xpath = "//*[@id='main']/header/h1")
  public WebElement loginFormHeader;

  @FindBy(name = "email")
  public WebElement emailInput;

  @FindBy(name = "password")
  public WebElement passwordInput;

  @FindBy(id = "submit-login")
  public WebElement submitBtn;

  @FindBy(xpath = "//li[@class = 'alert alert-danger']")
  public WebElement alert;

  @FindBy(xpath = "//*[@id='login-form']/section/div[2]/div[1]/div/span/button")
  public WebElement showHideBtn;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

}
