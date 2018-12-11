package pl.soflab.selenium.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"classpath:features/Login.features"},
    glue = "pl.soflab.selenium.steps",
    strict = true,
    plugin = {"html:target/cukes", "pretty"},
    tags = {"@stage", "not @out_of_scope"}
)
public class TestRunner {
}
