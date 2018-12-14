package pl.soflab.selenium.runners;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("pl.soflab.selenium")
@IncludeTags({"chrome", "firefox", "edge"})
@ExcludeTags("junit")
public class RealBrowserRunner {
}
