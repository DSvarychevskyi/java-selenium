package pl.soflab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.soflab.selenium.pages.AccountPage;
import pl.soflab.selenium.pages.LoginPage;
import pl.soflab.selenium.pages.MainPage;
import pl.soflab.selenium.pages.Page;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {

  static WebDriver driver;
  static ChromeOptions options;
  static Page page;
  static MainPage mainPage;
  static LoginPage loginPage;
  static AccountPage accountPage;

  @BeforeAll
  static void setUpAll() {
    options = new ChromeOptions();
    options.setHeadless(true);
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
  }

  @AfterEach
  void setDownEach() {
    driver.quit();
  }
}
