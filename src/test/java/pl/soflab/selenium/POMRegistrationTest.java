package pl.soflab.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class POMRegistrationTest extends FunctionalTest {

  @Test
  void registerTest() {
    page.openUrl("http://demo.prestashop.com", "PrestaShop Demo");
    page.swichToMainFrame();
    mainPage.gotoLoginPage();
    loginPage.gotoRegistrationPage();
    assertEquals("Create an account", registrationPage.header.getText());
    registrationPage.fillinFormAndSave(0, "Name", "Lastname", generateEmail()+"@wp.pl",
        "test123", getDate(), true, true);
    assertEquals("Name Lastname", mainPage.loggedUserLink.getText());
  }


}
