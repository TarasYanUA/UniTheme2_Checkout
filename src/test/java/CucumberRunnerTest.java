import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "steps"},
        //tags = "@10 or @20 or @30 or @40",  //Ultimate
        tags = "@110 or @120 or @130 or @140",    //Multivendor
        //tags = "@210 or @220 or @230 or @240",  // под мобильное устройство (Ultimate)
        plugin = {"pretty", "html:target/cucumber_target.html", "json:target/cucumber.json"}
)
public class CucumberRunnerTest {
}