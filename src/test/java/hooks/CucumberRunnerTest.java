package hooks;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "steps"},
        dryRun = true
)
@Test
public class CucumberRunnerTest {
}