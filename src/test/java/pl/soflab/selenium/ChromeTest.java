package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChromeTest {

  static Logger log = Logger.getLogger(ChromeTest.class);
  private static WebDriver driver;
  private static ChromeOptions options;

  @BeforeAll
  static void setUpAll() {
    options = new ChromeOptions();
    options.setHeadless(true);
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
  }

  @BeforeEach
  void setUpEach() {
    log.info("Start Test");
    driver = new ChromeDriver(options);
    WebDriver.Options manage = driver.manage();
    manage.window().maximize();
    manage.timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  void testExample() {
    driver.get("https://www.wikipedia.org");
    WebElement searchInput = driver.findElement(By.id("searchInput"));
    searchInput.sendKeys("Selenium");
    searchInput.submit();
    assertEquals("Selenium - Wikipedia", driver.getTitle());
  }

  @AfterEach
  void setDownEach() {
    driver.quit();
    log.info("End Test");
  }
}
