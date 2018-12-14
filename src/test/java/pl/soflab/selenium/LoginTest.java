package pl.soflab.selenium;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

  static Logger log = Logger.getLogger(LoginTest.class);
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
  private StringBuffer verificationErrors = new StringBuffer();
  @Test
  public void testLoginTest2Case() throws Exception {
    driver.get("http://demo.prestashop.com/en/?view=front");
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | index=0 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=''])[1]/following::span[1]")).click();
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("1001test@test.pl");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test1234");
    driver.findElement(By.id("submit-login")).click();
    try {
      assertEquals("Your account", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Home'])[1]/following::h1[1]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  @Test
  public void testLoginTestCase() throws Exception {
    driver.get("http://demo.prestashop.com/en/?view=front");
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | index=0 | ]]
    driver.switchTo().frame(driver.findElement(By.id("framelive")));
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=''])[1]/following::span[1]")).click();
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("1001test@test.pl");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test1234");
    driver.findElement(By.id("submit-login")).click();

    assertEquals("Your account", driver.findElement(By.xpath("//*[@id='main']//h1")).getText());
//*[@id="main"]/header/h1
  }

  @Test
  @DisplayName("Login to presta shop")
  void loginTest() throws InterruptedException {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
    WebElement emailInput = driver.findElement(By.name("email"));
    emailInput.sendKeys("1001test@test.pl");
    WebElement passwordInput = driver.findElement(By.name("password"));
    passwordInput.sendKeys("test1234");
    passwordInput.submit();
    assertEquals("Your account", driver.findElement(By.xpath("//section[contains(@id, 'main')]//h1")).getText());
  }

  @Test
  @DisplayName("Failed Login (wrong username) to presta shop")
  void failedLoginWrongUsernameTest() {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
    WebElement emailInput = driver.findElement(By.name("email"));
    emailInput.sendKeys("2002test@test.pl");
    WebElement passwordInput = driver.findElement(By.name("password"));
    passwordInput.sendKeys("test1234");
    driver.findElement(By.id("submit-login")).click();
    // lub passwordInput.submit();
    assertEquals("Authentication failed.",
        driver.findElement(By.xpath("//li[@class = 'alert alert-danger']")).getText());

  }

  @Test
  @DisplayName("Failed Login (wrong password) to presta shop")
  void failedLoginWrongPasswordTest() {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
    WebElement emailInput = driver.findElement(By.name("email"));
    emailInput.sendKeys("1001test@test.pl");
    WebElement passwordInput = driver.findElement(By.name("password"));
    passwordInput.sendKeys("test1111");
    driver.findElement(By.id("submit-login")).click();
    // lub passwordInput.submit();
    assertEquals("Authentication failed.",
        driver.findElement(By.xpath("//li[@class = 'alert alert-danger']")).getText());

  }

  @Test
  @DisplayName("Failed Login (short password) to presta shop")
  void tryLoginWithShortPasswordTest() {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
    WebElement emailInput = driver.findElement(By.name("email"));
    emailInput.sendKeys("1001test@test.pl");
    WebElement passwordInput = driver.findElement(By.name("password"));
    passwordInput.sendKeys("test");
    passwordInput.submit();
    assertEquals("Invalid format.",
        driver.findElement(By.xpath("//li[@class = 'alert alert-danger']")).getText());
  }

  @Test
  @DisplayName("Failed Login (invalid email format) to presta shop")
  void tryLoginWithiInvalidEmailFormatTest() {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
    WebElement emailInput = driver.findElement(By.name("email"));
    emailInput.sendKeys("1001test");
    WebElement passwordInput = driver.findElement(By.name("password"));
    passwordInput.sendKeys("test1234");
    passwordInput.submit();
    assertEquals("Invalid format.",
        driver.findElement(By.xpath("//li[@class = 'alert alert-danger']")).getText());
  }

  @Test
  @DisplayName("Login to presta shop")
  void loginShowHidePasswordTest() throws InterruptedException {
    driver.get("http://demo.prestashop.com");
    assertEquals("PrestaShop Demo", driver.getTitle());
    WebElement mainFrame = driver.findElement(By.id("framelive"));
    driver.switchTo().frame(mainFrame);
    driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
    WebElement emailInput = driver.findElement(By.name("email"));
    emailInput.sendKeys("1001test@test.pl");
    WebElement passwordInput = driver.findElement(By.name("password"));
    passwordInput.sendKeys("test1234");
    assertEquals("password", passwordInput.getAttribute("type"));
    WebElement showHideBtn = driver.findElement(By.xpath("//*[@id='login-form']/section/div[2]/div[1]/div/span/button"));
    assertEquals("SHOW", showHideBtn.getText());
    showHideBtn.click();
    assertEquals("text", passwordInput.getAttribute("type"));
    assertEquals("HIDE", showHideBtn.getText());
    showHideBtn.click();
    assertEquals("password", passwordInput.getAttribute("type"));
    passwordInput.submit();
    assertEquals("Your account", driver.findElement(By.xpath("//section[contains(@id, 'main')]//h1")).getText());
  }

  @AfterEach
  void setDownEach() {
//    driver.quit();
  }
}
