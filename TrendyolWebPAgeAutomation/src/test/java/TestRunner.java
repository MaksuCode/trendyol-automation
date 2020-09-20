import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@cucumber.api.CucumberOptions(features = "src\\test\\resources\\features",
        tags = "@Login",
        glue = {"src\\main\\java\\stepdefinitions\\MyStepdefs.java"},
        plugin = {"html:target/cucumber-html-report"}
)

public class TestRunner {



}
