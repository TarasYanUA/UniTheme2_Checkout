package hooks;

import com.codeborne.selenide.*;
import org.assertj.core.api.SoftAssertions;
import static com.codeborne.selenide.Selenide.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.time.Duration;

//НЕ УДАЛЯТЬ
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class DriverHooks {
    public static final String BASIC_URL = "https://trs.test.abt.team/4183ultru/admin.php?dispatch=addons.manage";

    public DriverHooks() {super();}

    @Before()
    public void openBrowser() {
        Configuration.browser = "chrome";
        open(BASIC_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран
        Configuration.screenshots = true; //делаем скриншоты при падении
        Configuration.timeout = 5000;   //настройка таймаута или Общая задержка

        SoftAssertions softAssertions = new SoftAssertions();
        CollectAssertMessages.setSoftAssertions(softAssertions);

        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
    }

/*    @Before() //под мобильное устройство
    public void prepareBrowser() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 12 Pro");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverRunner.setWebDriver(driver);   // Устанавливаем созданный драйвер как текущий драйвер для Selenide
        open(BASIC_URL);
        Configuration.timeout = 2000; //Общая задержка
        Configuration.screenshots = true; //делаем скриншоты при падении

        SoftAssertions softAssertions = new SoftAssertions();
        CollectAssertMessages.setSoftAssertions(softAssertions);

        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
        if ($(".close.cm-notification-close").exists())
            $(".close.cm-notification-close").click();
        Selenide.sleep(1000);
    }*/

    @After
    public void closerBrowser() {
        SoftAssertions softAssertions = CollectAssertMessages.getSoftAssertions();
        try {
            softAssertions.assertAll();
        } catch (AssertionError e) {
            System.out.println("\nОшибки в asserts:");
            System.out.println(e.getMessage());
        }

        closeWebDriver();
    }

    public static void waitForSpinnerDisappear() {
        $("div#ajax_loading_box[style=\"display: block;\"]").shouldBe(Condition.disappear, Duration.ofSeconds(10));
        Selenide.sleep(1000);
    }
}