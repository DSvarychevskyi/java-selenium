package pl.soflab.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
        "test123", "01/01/1990", true, true);
    assertEquals("Name Lastname", mainPage.loggedUserLink.getText());
  }


}
