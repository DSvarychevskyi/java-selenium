package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {

  static Logger log = Logger.getLogger(RegistrationTest.class);
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
  @DisplayName("Rejestracja nowego użytkownika")
  void registerTest() {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();

    driver.findElement(By.xpath("*[@id='content']/div/a")).click();
    assertEquals("Create an account",
        driver.findElement(By.xpath("//section[contains(@id, 'main')]//h1")).getText());

    List<WebElement> genders = driver.findElements(By.name("id_gender"));
    genders.get(0).click();
    driver.findElement(By.name("firstname")).sendKeys("Name");
    driver.findElement(By.name("lastname")).sendKeys("LastName");


  }

  ////*[@id="content"]/div/a



  @AfterEach
  void setDownEach() {
//    driver.quit();
  }
}
//following-sibling::div/