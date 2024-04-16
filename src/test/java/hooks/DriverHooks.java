package hooks;

import com.codeborne.selenide.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.*;

public class DriverHooks {
    public static final String BASIC_URL = "https://trs.test.abt.team/4172ultru_unitheme2/admin.php?dispatch=settings.manage&section_id=Appearance";

    public DriverHooks() {super();}

    @Before()
    public void openBrowser() {
        Configuration.browser = "chrome";
        open(BASIC_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран
        Configuration.holdBrowserOpen = false; //не закрываем браузер пока ведём разработку
        Configuration.screenshots = true; //делаем скриншоты при падении

        SoftAssertions softAssertions = new SoftAssertions();
        CollectAssertMessages.setSoftAssertions(softAssertions);

        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
    }
    @After
    public void closerBrowser() {
        screenshot("Finish " + System.currentTimeMillis());

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