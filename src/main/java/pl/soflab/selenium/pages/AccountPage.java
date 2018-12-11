package pl.soflab.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends PageObject {

  @FindBy(xpath = "//section[contains(@id, 'main')]//h1")
  public WebElement accountSectionHeader;

  public AccountPage(WebDriver driver) {
    super(driver);
  }
}
