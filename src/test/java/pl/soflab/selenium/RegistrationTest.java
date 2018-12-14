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

  @ParameterizedTest(name = " #{index} Wybierz opcje: {arguments}")
  @ValueSource(ints = {0, 1})
  @DisplayName("Rejestracja nowego użytkownika")
  void registerTest(int position) {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();

    driver.findElement(By.xpath("//*[@id='content']/div/a")).click();
    assertEquals("Create an account",
        driver.findElement(By.xpath("//section[contains(@id, 'main')]//h1")).getText());

    List<WebElement> genders = driver.findElements(By.name("id_gender"));
    genders.get(position).click();
    driver.findElement(By.name("firstname")).sendKeys("Name");
    driver.findElement(By.name("lastname")).sendKeys("LastName");
    driver.findElement(By.name("email")).sendKeys(generateEmail()+"@mail.com");
    driver.findElement(By.name("password")).sendKeys("password1!");
    WebElement offertsCheckBox = driver.findElement(By.name("optin"));
    offertsCheckBox.click();
    assertTrue(offertsCheckBox.isSelected());
    driver.findElement(By.xpath("//*[@id='customer-form']/footer/button")).click();

    assertEquals("Name LastName",
        driver.findElement(By.xpath("//*[@id='_desktop_user_info']//a[2]")).getText());
  }

  private String generateEmail(){
    String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder builder = new StringBuilder();
    int count = 6;
    while (count-- != 0) {
      int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
      builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }
    log.info(builder.toString());
    return builder.toString();
  }

  ////*[@id="content"]/div/a



  @AfterEach
  void setDownEach() {
//    driver.quit();
  }
}
//following-sibling::div/