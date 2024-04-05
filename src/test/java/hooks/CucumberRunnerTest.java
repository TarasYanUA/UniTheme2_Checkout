package hooks;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;

import io.cucumber.java.AfterStep;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.util.*;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "steps"},
        dryRun = false
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    public static final String BASIC_URL = "https://trs.test.abt.team/4172ultru_unitheme2/admin.php?dispatch=settings.manage&section_id=Appearance";
    @BeforeClass
    public void openBrowser() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false; //не закрываем браузер пока ведём разработку
        Configuration.screenshots = true; //делаем скриншоты при падении
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран
        open(BASIC_URL);
        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
    }
    @AfterClass
    public void closerBrowser() {
        Selenide.closeWebDriver();
    }
    @AfterStep
    public void assertUniqueIDOnPage() {
        SoftAssert softAssert = new SoftAssert();
        // Получение коллекции всех элементов с заданным селектором
        ElementsCollection collectionOfId = $$x("//*[@id]");
        // Преобразование коллекции в множество
        Set<SelenideElement> uniqueElements = new HashSet<>((Collection) collectionOfId);
        // Проверка, что размер множества равен размеру исходной коллекции
        boolean isUnique = uniqueElements.size() == collectionOfId.size();
        softAssert.assertTrue(isUnique, "Коллекция ID на странице НЕ уникальна!");
    }
/*    @AfterStep // Действия совершаемые после каждого шага
    public void takeScreenShotAfterStep(Scenario scenario) {
        Selenide.screenshot(System.currentTimeMillis() + "steps");
    }*/

    public void shiftBrowserTab(int tabNumber) {
        WebDriverRunner.getWebDriver().getWindowHandle();
        switchTo().window(tabNumber);
    }
    public void selectLanguage(String lang_RuEnAr) {
        $("a[id*='_wrap_language_']").hover().click();
        $(".ty-select-block__list-item a[data-ca-name='" + lang_RuEnAr + "']").click();
    }
}