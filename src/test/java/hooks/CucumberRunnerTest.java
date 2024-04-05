package hooks;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "steps"}
)
public class CucumberRunnerTest {
}