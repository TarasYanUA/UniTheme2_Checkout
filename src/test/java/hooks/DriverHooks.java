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
        //Собираем первую коллекцию по селектору
        ElementsCollection collectionOne_currentID = $$x("//*[@id]");
        // Направляем значения из первой коллекции во вторую
        List<String> collectionTwo_listOfID = new ArrayList<>();
        for (SelenideElement element : collectionOne_currentID) {
            String idValue = element.getAttribute("id");
            collectionTwo_listOfID.add(idValue);
        }
        // Поиск повторяющихся ID
        Set<String> collectionThree_duplicatedID = new HashSet<>();
        for (String idValue : collectionTwo_listOfID) {
            // Если ID уже присутствует в коллекции collectionThree_duplicatedID, то это повторяющийся ID
            if (collectionTwo_listOfID.indexOf(idValue) != collectionTwo_listOfID.lastIndexOf(idValue)) {
                collectionThree_duplicatedID.add(idValue);
            }
        }
        // Вывод на экран неуникальных ID
        if(!collectionThree_duplicatedID.isEmpty()) {
            System.out.println("Коллекция ID НЕ уникальна на странице: " + currentUrl + " Список не уникальных ID: ");
            for (String id : collectionThree_duplicatedID) {
                System.out.print(id + ", ");
                System.out.println();
            }
        }
    }   //myList.forEach(item -> System.out.println(item)); //лямбда-выражение
    @After
    public void closerBrowser() {
        Selenide.closeWebDriver();
    }
}
