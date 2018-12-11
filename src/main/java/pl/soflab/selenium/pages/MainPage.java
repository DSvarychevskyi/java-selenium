package pl.soflab.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject{

  @FindBy(xpath = "//span[contains(text(), 'Sign in')]")
  WebElement loginLink;

  public MainPage(WebDriver driver) {
    super(driver);
  }

  public void gotoLoginPage() {
    loginLink.click();
  }
}
