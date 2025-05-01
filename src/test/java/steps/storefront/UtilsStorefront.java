package steps.storefront;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class UtilsStorefront {

    // Метод для закрытия уведомлений
    public static void safeCloseNotifications() {
        sleep(2000);
        while ($(".cm-notification-close").exists()) {
            try {
                SelenideElement notification = $(".cm-notification-close");
                if (notification.exists()) { // ещё раз проверяем существование элемента перед кликом
                    notification.click();
                    sleep(300);
                }
            } catch (Exception e) {
                System.out.println("Ошибка при закрытии уведомления: " + e.getMessage());
                break;
            }
        }
    }

    // Метод на ожидание того, что спиннер исчез
    public static void waitForSpinnerDisappear() {
        $("div#ajax_loading_box[style=\"display: block;\"]").shouldBe(Condition.disappear, Duration.ofSeconds(10));
        sleep(1000);
    }

    public static void selectMethodFromList(String methodName, String methodXpath, String screenshotName) {
        String formattedXpath = methodXpath.replace("{methodName}", methodName);
        SelenideElement element = $x(formattedXpath);
        executeJavaScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", element);
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