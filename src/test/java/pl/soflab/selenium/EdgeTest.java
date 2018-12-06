package pl.soflab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class EdgeTest {

  private static WebDriver driver;

  @BeforeAll
  static void setUpAll() {
    System.setProperty("webdriver.edge.driver", "src/test/resources/MicrosoftWebDriver.exe");
  }

  @BeforeEach
  void setUpEach() {
    System.out.println("Start Test");
    driver = new EdgeDriver();
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
