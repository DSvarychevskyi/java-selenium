package pl.soflab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitTest {

  private static HtmlUnitDriver driver;

  @BeforeAll
  static void setUpAll() {
  }

  @BeforeEach
  void setUpEach() {
    System.out.println("Start Test");
    driver = new HtmlUnitDriver();
  }

  @Test
  void testExample() {
    driver.get("https://www.wikipedia.org");
    WebElement searchInput = driver.findElement(By.id("searchInput"));
    searchInput.sendKeys("Selenium");
    searchInput.submit();
  }

  @AfterEach
  void setDownEach() {
    driver.quit();
    System.out.println("End Test");
  }
}
