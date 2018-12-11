package pl.soflab.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.datatransfer.StringSelection;

public class Page extends PageObject {

  @FindBy(id = "framelive")
  private WebElement mainFrame;

  public Page(WebDriver driver) {
    super(driver);
  }

  public void swichToMainFrame(){
    driver.switchTo().frame(this.mainFrame);
  }
}
