package steps.storefront;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class UtilsStorefront {

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

    // Метод для выбора способа доставки из списка и ожидания исчезновения спиннера
    public static void selectShippingMethodFromList(String shippingMethod, String xpath, String screenshot) {
        String formattedXpath = xpath.replace("{shippingMethod}", shippingMethod);
        clickIfNotSelected($x(formattedXpath));
        waitForSpinnerDisappear();
        screenshot(screenshot + " ShippingMethod");
    }

    // Метод для выбора способа оплаты из выпадающего списка
    public static void selectPaymentMethodFromDropDown(String paymentMethod, String screenshot) {
        String xpath = "//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]";
        if (!$x(xpath).isDisplayed()) {
            $(".b--pay-way__opted__text__title.b--pay-ship__opted__text__title").scrollIntoCenter().click();
            $x(xpath).hover();
            screenshot(screenshot + " PaymentMethod DropdownList");
            $x(xpath).click();
            waitForSpinnerDisappear();
            screenshot(screenshot + " PaymentMethod");
        }
    }

    // Метод для выбора способа оплаты из обычного списка
    public static void selectPaymentMethodFromSimpleList(String paymentMethod, String xpath, String screenshot) {
        if (!$x(xpath).exists())
            $x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").click();
        waitForSpinnerDisappear();
        screenshot(screenshot + " PaymentMethod");
    }
}