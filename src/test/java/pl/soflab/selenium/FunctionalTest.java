package pl.soflab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.soflab.selenium.pages.*;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {

  static WebDriver driver;
  static ChromeOptions options;
  static Page page;
  static MainPage mainPage;
  static LoginPage loginPage;
  static AccountPage accountPage;
  static RegistrationPage registrationPage;

  @BeforeAll
  static void setUpAll() {
    options = new ChromeOptions();
    options.setHeadless(false);
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
  }

  @BeforeEach
  void setUpEach() {
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    page = new Page(driver);
    mainPage = new MainPage(driver);
    loginPage = new LoginPage(driver);
    accountPage = new AccountPage(driver);
    registrationPage = new RegistrationPage(driver);
  }

  @AfterEach
  void setDownEach() {
//    driver.quit();
  }

  public String generateEmail(){
    String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder builder = new StringBuilder();
    int count = 6;
    while (count-- != 0) {
      int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
      builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }
    return builder.toString();
  }
}
