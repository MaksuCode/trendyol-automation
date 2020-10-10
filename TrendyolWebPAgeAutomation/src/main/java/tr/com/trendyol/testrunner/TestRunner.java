package tr.com.trendyol.testrunner;



import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
                 tags = "@Login",
                 glue = {"cucumber.stepdefinitions"},
                 plugin = {"html:target/cucumber-html-report"}
)

public class TestRunner {
}
