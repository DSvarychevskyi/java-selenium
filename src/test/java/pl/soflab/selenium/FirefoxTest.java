package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FirefoxTest {

  static Logger log = Logger.getLogger(FirefoxTest.class);
  private static WebDriver driver;
  private static FirefoxOptions options;

  @BeforeAll
  static void setUpAll() {
    options = new FirefoxOptions();
    options.setHeadless(true);
    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "logs/firefox-log.log");
    System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
  }

  @BeforeEach
  void setUpEach() {
    log.info("Start Test");
    driver = new FirefoxDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  @Tag("firefox")
  void testExample() {
    driver.get("https://www.wikipedia.org");
    driver.findElement(By.id("searchInput"));
    driver.findElement(By.id("searchInput")).sendKeys("Selenium");
    driver.findElement(By.id("searchInput")).submit();
  }

  @AfterEach
  void setDownEach() {
    driver.quit();
    log.info("End Test");
  }
}
