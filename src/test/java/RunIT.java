import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", tags = {"@runMe"}) //, format = {"pretty", "html:target/cucumber"})

public class RunIT { }
