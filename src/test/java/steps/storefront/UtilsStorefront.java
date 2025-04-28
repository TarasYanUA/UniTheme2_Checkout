package steps.storefront;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class UtilsStorefront {

    // Метод для безопасного закрытия уведомлений
    public static void safeCloseNotifications() {
        SelenideElement notification = $(".cm-notification-close");
        if (notification.exists()) {
            try {
                notification.click();
            } catch (Exception e) {
                System.out.println("Notification close button not found or could not be clicked: " + e.getMessage());
            }
        }
    }

    // Метод на ожидание того, что спиннер исчез
    public static void waitForSpinnerDisappear() {
        $("div#ajax_loading_box[style=\"display: block;\"]").shouldBe(Condition.disappear, Duration.ofSeconds(10));
        Selenide.sleep(1000);
    }

    // Универсальный метод для клика по элементу с проверкой его существования
    public static void clickIfNotSelected(SelenideElement element) {
        if (!element.exists() && element.isDisplayed()) {
            element.click();
        } else {
            System.out.println("Element not found or not visible: " + element);
        }
    }

    public static void selectMethodFromList(String methodName, String xpathTemplate, String screenshotName) {
        String formattedXpath = xpathTemplate.replace("{methodName}", methodName);
        clickIfNotSelected($x(formattedXpath));
        waitForSpinnerDisappear();
        screenshot(screenshotName);
    }

    public static void selectMethodFromDropDown(String methodName, String methodXpath, String dropdownSelector, String screenshotName) {
        String formattedXpath = methodXpath.replace("{methodName}", methodName);
        $(dropdownSelector).scrollIntoCenter().click();
        screenshot(screenshotName + " DropdownList");
        executeJavaScript("arguments[0].click();", $x(formattedXpath));
        waitForSpinnerDisappear();
        screenshot(screenshotName);
    }
}