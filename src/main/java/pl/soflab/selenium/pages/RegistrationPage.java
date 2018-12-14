package pl.soflab.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage extends PageObject {
  public RegistrationPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//section[contains(@id, 'main')]//h1")
  public WebElement header;

  @FindBy(name = "id_gender")
  public List<WebElement> genders;

  @FindBy(name = "firstname")
  public WebElement nameInput;

  @FindBy(name = "lastname")
  public WebElement lastInput;

  @FindBy(name = "email")
  public WebElement emailInput;

  @FindBy(name = "password")
  public WebElement passwordInput;

  @FindBy(name = "birthday")
  public WebElement dateInput;

  @FindBy(name = "optin")
  public WebElement offertsCheckbox;

  @FindBy(name = "newsletter")
  public WebElement newslatterCheckbox;

  @FindBy(xpath = "//*[@id='customer-form']/footer/button")
  public WebElement saveBtn;

  public void fillinFormAndSave(int genderIndex, String firstname, String lastname, String email,
                                String password, String date, Boolean offerts, Boolean newslater){
    genders.get(genderIndex).click();
    nameInput.sendKeys(firstname);
    lastInput.sendKeys(lastname);
    emailInput.sendKeys(email);
    passwordInput.sendKeys(password);
    dateInput.sendKeys(date);
    if (offerts) {
      offertsCheckbox.click();
      assertTrue(offertsCheckbox.isSelected());
    }
    if (newslater) {
      newslatterCheckbox.click();
      assertTrue(newslatterCheckbox.isSelected());
    }
    saveBtn.click();
  }

}
