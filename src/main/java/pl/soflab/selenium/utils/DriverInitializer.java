package pl.soflab.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.Properties;

public class DriverInitializer {
  private static Properties properties = null;

  static {
    try {
      properties = new Properties();
      properties.load(DriverInitializer.class.getClassLoader().getResourceAsStream("application.properties"));
      System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.path"));
      System.setProperty("webdriver.gecko.driver", properties.getProperty("gecko.path"));
      System.setProperty("webdriver.edge.driver", properties.getProperty("edge.path"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  private static WebDriver driver;
  public static WebDriver getInstance() {
    if (driver == null) {
      switch (Objects.requireNonNull(properties.getProperty("browser"))) {
        case "chrome":
          ChromeOptions options = new ChromeOptions();
          options.setHeadless(Boolean.parseBoolean(properties.getProperty("headless")));
          driver = new ChromeDriver(options);
          break;
        case "firefox":
          driver = new FirefoxDriver();
          break;
        case "edge":
          driver = new EdgeDriver();
          break;
        default:
          driver = new ChromeDriver();
      }
    }
    return driver;
  }

  public static void quit() {
    driver.quit();
    driver = null;
  }
}
