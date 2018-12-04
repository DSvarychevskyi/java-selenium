package pl.soflab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
    }

    @BeforeEach
    void setUpEach() {
        System.out.println("Start Test");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void testExample() {
        driver.get("http://google.com");
    }

    @AfterEach
    void setDownEach() {
        driver.close();
        System.out.println("End Test");
    }
}
