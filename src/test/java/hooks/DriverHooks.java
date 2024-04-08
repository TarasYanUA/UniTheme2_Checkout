package hooks;

import com.codeborne.selenide.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;

import java.util.*;

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
    @AfterStep
    public void assertUniqueIDOnPage() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        ElementsCollection collectionOfId = $$x("//*[@id]");
        // Создание множества для хранения уникальных значений
        Set<String> uniqueValues = new HashSet<>();
        // Проверка уникальности значений
        for (SelenideElement element : collectionOfId) {
            // Получение значения атрибута "id" и добавление его в множество
            String idValue = element.getAttribute("id");
            uniqueValues.add(idValue);
        }
        // Проверка, что размер множества равен размеру коллекции (все значения уникальны)
        boolean isUnique = uniqueValues.size() == collectionOfId.size();
        if(isUnique == false) {
            System.out.println("Коллекция ID НЕ уникальна на странице: " + currentUrl);
        }
    }
    @After
    public void closerBrowser() {
        Selenide.closeWebDriver();
    }

    /*    @AfterStep // Действия совершаемые после каждого шага
    public void takeScreenShotAfterStep(Scenario scenario) {
        Selenide.screenshot(System.currentTimeMillis() + "steps");
    }*/
}
