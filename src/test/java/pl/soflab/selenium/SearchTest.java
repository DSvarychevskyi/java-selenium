package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("regression")
public class SearchTest {

  static Logger log = Logger.getLogger(SearchTest.class);
  private static WebDriver driver;
  private static ChromeOptions options;

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
  }

  @Test
  @DisplayName("Wyszukaj produkt i sprawdź rezultat")
  void searchTest() {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    WebElement search = driver.findElement(By.name("s"));
    search.sendKeys("t-shirt");
    search.submit();
    assertTrue(driver.findElements(By.xpath("//div//article")).size() > 0);
  }

  @Test
  @DisplayName("Wyszukaj produkt i wejdź do niego")
  void serchAndEnterTest() {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    WebElement search = driver.findElement(By.name("s"));
    search.sendKeys("t-shirt");
    driver.findElement(By.className("product")).click();
    WebElement productElement = driver.findElement(By.id("main"));
    assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("T-SHIRT"));
  }

  @AfterEach
  void setDownEach() {
    driver.quit();
  }
}