package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"stepdefs", "Hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        //tags = "@functionality",   // Optional: tag filtering
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {}
