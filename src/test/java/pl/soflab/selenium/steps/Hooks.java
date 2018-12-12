package pl.soflab.selenium.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import pl.soflab.selenium.utils.DriverInitializer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks {

  WebDriver driver = DriverInitializer.getInstance();

  @Before
  public void beforeScenario() {
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @After
  public void afterScenario(Scenario scenario) {
    if (scenario.isFailed()){
      TakesScreenshot browser = (TakesScreenshot) new Augmenter().augment(driver);
      final byte[] screenshot = browser.getScreenshotAs(OutputType.BYTES);
      scenario.embed(screenshot, "image/png");
      scenario.write("URL: " + driver.getCurrentUrl());
    }
    DriverInitializer.quit();
  }
}
