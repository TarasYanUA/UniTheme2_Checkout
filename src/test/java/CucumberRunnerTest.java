import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "steps"},
        tags = "@Ult020",
        //tags = "@Ult010 or @Ult020 or @Ult030 or @Ult040",  //Ultimate
        //tags = "@MV110 or @MV120 or @MV130 or @MV140",    //Multi-Vendor
        //tags = "@Mob210 or @Mob220",  // под мобильное устройство (Ultimate)
        plugin = {"pretty", "html:target/cucumber_target.html", "json:target/cucumber.json"}
)
public class CucumberRunnerTest {
}