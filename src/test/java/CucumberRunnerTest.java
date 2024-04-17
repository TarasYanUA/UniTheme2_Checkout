import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "steps"},
        tags = "@20",
        plugin = {"pretty", "html:target/cucumber_target.html", "json:target/cucumber.json"}
)
public class CucumberRunnerTest {
}