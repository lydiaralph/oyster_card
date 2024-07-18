package lydia.ralph.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"api/src/integration/resources/lydia/ralph/api"},
        plugin = {"pretty", "html:target/cucumber"},
        glue = "lydia.ralph.api.features")
public class OysterServiceIntegrationTest {
}
