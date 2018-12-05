package pl.soflab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;

public class HtmlUnitTest {

    private static HtmlUnitDriver driver;

    @BeforeAll
    static void setUpAll() {
    }

    @BeforeEach
    void setUpEach() {
        System.out.println("Start Test");
        driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
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
