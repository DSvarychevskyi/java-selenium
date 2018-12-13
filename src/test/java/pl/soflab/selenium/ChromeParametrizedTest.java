package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeParametrizedTest {

  static Logger log = Logger.getLogger(ChromeParametrizedTest.class);
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
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Tag("parametrized")
  @ParameterizedTest(name = "Run #{index} with [{arguments}]")
  @ValueSource(strings = { "Selenium", "JUnit5" })
  void testExample(String param) {
    driver.get("https://www.wikipedia.org");
    driver.findElement(By.id("searchInput"));
    driver.findElement(By.id("searchInput")).sendKeys(param);
    driver.findElement(By.id("searchInput")).submit();
  }

  @AfterEach
  void setDownEach() {
    driver.quit();
    log.info("End Test");
  }
}
