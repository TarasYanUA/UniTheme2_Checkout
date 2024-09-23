package hooks;

import com.codeborne.selenide.*;
import org.assertj.core.api.SoftAssertions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class DriverHooks {
    public static final String BASIC_URL = "https://trs.test.abt.team/4182ultru/admin.php?dispatch=themes.manage";

    public DriverHooks() {super();}

/*    @Before()
    public void openBrowser() {
        Configuration.browser = "chrome";
        open(BASIC_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран
        Configuration.holdBrowserOpen = false; //не закрываем браузер пока ведём разработку
        Configuration.screenshots = true; //делаем скриншоты при падении
        Configuration.timeout = 5000;   //настройка таймаута или Общая задержка

        SoftAssertions softAssertions = new SoftAssertions();
        CollectAssertMessages.setSoftAssertions(softAssertions);

        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
    }*/

    @Before() //под мобильное устройство
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
        $(".close.cm-notification-close").click();
        Selenide.sleep(1000);
    }

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
}