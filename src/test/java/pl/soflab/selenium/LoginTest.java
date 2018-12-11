package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.soflab.selenium.pages.AccountPage;
import pl.soflab.selenium.pages.LoginPage;
import pl.soflab.selenium.pages.MainPage;
import pl.soflab.selenium.pages.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends FunctionalTest{

  static Logger log = Logger.getLogger(LoginTest.class);

  @Test
  @DisplayName("Login to presta shop")
  void pomLoginTest() throws InterruptedException {
    Page page = new Page(driver);
    MainPage mainPage = new MainPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    AccountPage accountPage = new AccountPage(driver);

    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    page.swichToMainFrame();
    mainPage.gotoLoginPage();
    loginPage.fillLoginForm("1001test@test.pl", "test1234");

    assertEquals("Your account", accountPage.accountSectionHeader.getText());
  }

  @Test
  @DisplayName("Failed Login (wrong username) to presta shop")
  void failedLoginWrongUsernameTest() {
    Page page = new Page(driver);
    MainPage mainPage = new MainPage(driver);
    LoginPage loginPage = new LoginPage(driver);

    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    page.swichToMainFrame();
    mainPage.gotoLoginPage();
    loginPage.fillLoginForm("2002test@test.pl", "test1234");

    assertEquals("Authentication failed.", loginPage.alert.getText());
  }

  @Test
  @DisplayName("Failed Login (wrong password) to presta shop")
  void failedLoginWrongPasswordTest() {
    Page page = new Page(driver);
    MainPage mainPage = new MainPage(driver);
    LoginPage loginPage = new LoginPage(driver);

    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    page.swichToMainFrame();
    mainPage.gotoLoginPage();
    loginPage.fillLoginForm("1001test@test.pl", "test1111");

    assertEquals("Authentication failed.", loginPage.alert.getText());
  }

  @Test
  @DisplayName("Failed Login (invalid email format) to presta shop")
  void tryLoginWithiInvalidEmailFormatTest() {
    Page page = new Page(driver);
    MainPage mainPage = new MainPage(driver);
    LoginPage loginPage = new LoginPage(driver);

    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    page.swichToMainFrame();
    mainPage.gotoLoginPage();
    loginPage.fillLoginForm("2002test", "test1234");

//    assertEquals("Invalid format.", loginPage.alert.getText());
    assertEquals("Please include an '@' in the email address. '2002test' is missing an '@'.",
        loginPage.emailInput.getAttribute("validationMessage"));
  }

  @Test
  @DisplayName("Failed Login (short password) to presta shop")
  void tryLoginWithShortPasswordTest() {
    Page page = new Page(driver);
    MainPage mainPage = new MainPage(driver);
    LoginPage loginPage = new LoginPage(driver);

    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    page.swichToMainFrame();
    mainPage.gotoLoginPage();
    loginPage.fillLoginForm("2002test@test.pl", "test");

//    assertEquals("Invalid format.", loginPage.alert.getText());
    assertEquals("Please match the format requested.",
        loginPage.passwordInput.getAttribute("validationMessage"));
  }

  @Test
  @DisplayName("Show/Hide password and login to presta shop")
  void loginShowHidePasswordTest() throws InterruptedException {
    Page page = new Page(driver);
    MainPage mainPage = new MainPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    AccountPage accountPage = new AccountPage(driver);

    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    page.swichToMainFrame();
    mainPage.gotoLoginPage();
    loginPage.emailInput.sendKeys("1001test@test.pl");
    loginPage.passwordInput.sendKeys("test1234");
    assertEquals("password", loginPage.passwordInput.getAttribute("type"));
    assertEquals("SHOW", loginPage.showHideBtn.getText());
    loginPage.showHideBtn.click();
    assertEquals("text", loginPage.passwordInput.getAttribute("type"));
    assertEquals("HIDE", loginPage.showHideBtn.getText());
    loginPage.submitBtn.click();

    assertEquals("Your account", accountPage.accountSectionHeader.getText());
  }

}
