package hooks;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"src/test/resources/features"},
        dryRun = false
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    @AfterStep // Действия совершаемые после каждого шага
    public void takeScreenShotAfterStep(Scenario scenario) {
        Selenide.screenshot(System.currentTimeMillis() + "steps");
    }
}