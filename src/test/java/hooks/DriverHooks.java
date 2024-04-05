package hooks;

import com.codeborne.selenide.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import org.testng.asserts.SoftAssert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;

public class DriverHooks {
    public static final String BASIC_URL = "https://trs.test.abt.team/4172ultru_unitheme2/admin.php?dispatch=settings.manage&section_id=Appearance";
    @Before
    public void openBrowser() {
        Configuration.browser = "chrome";
        open(BASIC_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран
        Configuration.holdBrowserOpen = false; //не закрываем браузер пока ведём разработку
        Configuration.screenshots = true; //делаем скриншоты при падении
        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
    }
    @After
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
