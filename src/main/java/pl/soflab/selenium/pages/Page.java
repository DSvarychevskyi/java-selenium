package pl.soflab.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Page extends PageObject {

  @FindBy(id = "framelive")
  private WebElement mainFrame;

  public Page(WebDriver driver) {
    super(driver);
  }

  public void openUrl(String url, String title) {
    driver.get(url);
    assertEquals(title, driver.getTitle());
  }
  public void swichToMainFrame(){
    driver.switchTo().frame(mainFrame);
  }
}
