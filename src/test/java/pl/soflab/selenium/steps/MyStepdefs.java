package pl.soflab.selenium.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.soflab.selenium.pages.AccountPage;
import pl.soflab.selenium.pages.LoginPage;
import pl.soflab.selenium.pages.MainPage;
import pl.soflab.selenium.pages.Page;
import pl.soflab.selenium.utils.DriverInitializer;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {

  static Logger log = Logger.getLogger(MyStepdefs.class);

  WebDriver driver;
  ChromeOptions options;
  Page page;
  MainPage mainPage;
  LoginPage loginPage;
  AccountPage accountPage;

  @Given("prepare browser")
  public void openChromeBrowser(String browser) {
    driver = DriverInitializer.getInstance();//new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    page = new Page(driver);
    mainPage = new MainPage(driver);
    loginPage = new LoginPage(driver);
    accountPage = new AccountPage(driver);
  }

  @Given("open ([^ ]+) page")
  public void openPage(String url) {
    driver.get(url);
    page.swichToMainFrame();
  }

  @When("click ([^ ]+) button")
  public void clickLoginButton(String buttonName) {
    switch (buttonName){
      case "LOGIN":
        mainPage.loginLink.click();
        break;
      case "SIGN_IN":
        loginPage.submitBtn.click();
        break;
    }

  }

  @Then("we should see login form")
  public void weShouldSeeLoginForm() {
    assertEquals("Log in to your account", loginPage.loginFormHeader.getText());
  }

  @When("enter ([^ ]+) into email field")
  public void enterTestTestPlIntoEmailField(String email) {
    loginPage.emailInput.sendKeys(email);
  }

  @And("enter ([^ ]+) into password field")
  public void enterTestIntoPasswordField(String password) {
    loginPage.passwordInput.sendKeys(password);
  }

  @Then("we should see (.+) message")
  public void weShouldSeeYourAccountMessage(String expectedMessage) {
    assertEquals(expectedMessage, accountPage.accountSectionHeader.getText());
  }

  @Then("we should see (.+) error alert")
  public void weShouldSeeErrorMessage(String expectedMessage) {
    assertEquals(expectedMessage, loginPage.alert.getText());
  }
}
