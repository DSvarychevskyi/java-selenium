package pl.soflab.selenium.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

  @Before
  public void beforeScenario() {
    System.out.println("-----------------Start of Scenario-----------------");
  }

  @After
  public void afterScenario() {
    System.out.println("-----------------End of Scenario-----------------");
  }
}
