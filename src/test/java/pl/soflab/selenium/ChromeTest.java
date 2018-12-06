package pl.soflab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeTest {

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
    System.out.println("Start Test");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  void testExample() {
    driver.get("http://google.com");
  }

  @AfterEach
  void setDownEach() {
    driver.quit();
    System.out.println("End Test");
  }
}
