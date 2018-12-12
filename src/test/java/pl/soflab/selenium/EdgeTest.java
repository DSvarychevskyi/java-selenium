package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class EdgeTest {

  static Logger log = Logger.getLogger(FirefoxTest.class);
  private static WebDriver driver;

  @BeforeAll
  static void setUpAll() {
    System.setProperty("webdriver.edge.driver", "src/test/resources/MicrosoftWebDriver.exe");
  }

  @BeforeEach
  void setUpEach() {
    log.info("Start Test");
    driver = new EdgeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  @Tag("edge")
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
