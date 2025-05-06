package steps.adminPanel;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.*;

public class BasicPageSteps {

    private final BasicPage basicPage;

    public BasicPageSteps(BasicPage basicPage) {
        this.basicPage = basicPage;
    }

    @Given("Переходим на страницу \"Веб-сайт -- Темы -- Макеты\"")
    public void navigateTo_LayoutPage() {
        basicPage.navigateTo_LayoutPage();
    }

    @When("Переходим на витрину")
    public void navigateToStorefront_HomePage() {
        basicPage.navigateToStorefront_HomePage();
    }

    @And("Добавляем изображение способу {string} {string}")
    public void addImageToMethod(String methodType, String methodName) {
        basicPage.openMenuOfSettings();

        switch (methodType.toLowerCase()) {
            case "доставки":
                basicPage.addImageToShippingMethod(methodName, "https://dummyimage.com/50x50/09f/fff.png&text=b2");
                break;
            case "оплаты":
                basicPage.addImageToPaymentMethod(methodName, "https://dummyimage.com/50x50/09f/fff.png&text=a1");
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип: \"" + methodType + "\". Ожидалось: \"доставки\" или \"оплаты\".");
        }
    }

    @And("Удаляем изображение способу {string} {string}")
    public void deleteImageFromMethod(String methodType, String methodName) {
        basicPage.openMenuOfSettings();

        switch (methodType.toLowerCase()) {
            case "доставки":
                basicPage.deleteImageFromShippingMethod(methodName);
                break;
            case "оплаты":
                basicPage.deleteImageFromPaymentMethod(methodName);
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип: \"" + methodType + "\". Ожидалось: \"доставки\" или \"оплаты\".");
        }
    }


    // Мобильное устройство
    @Given("Переходим на страницу \"Веб-сайт -- Темы -- Макеты\" \\(mobile)")
    public void navigateTo_LayoutPage__mobile() {
        basicPage.navigateTo_LayoutPage__mobile();
    }

    @And("Удаляем изображение способу {string} {string} \\(mobile)")
    public void deleteImageFromMethod__mobile(String methodType, String methodName) {
        boolean isShipping = methodType.equalsIgnoreCase("доставки");
        boolean isPayment = methodType.equalsIgnoreCase("оплаты");

        if (!isShipping && !isPayment) {
            throw new IllegalArgumentException("Неизвестный тип: " + methodType);
        }

        basicPage.mobile_MainMenu.click();
        basicPage.openMenuOfSettings();

        if (isShipping) {
            basicPage.section_ShippingMethod.click();
        } else {
            basicPage.section_PaymentMethod.click();
        }

        String xpath = isShipping
                ? "//div[@id='shippings_content']//a[text()='" + methodName + "']"
                : "//a[text()='" + methodName + "']";

        basicPage.jsScrollAndClick($x(xpath));
        basicPage.popupWindow.shouldBe(Condition.exist);

        String scrollSelector = isShipping
                ? "input[id*='alt_icon_shipping_']"
                : "input[id*='alt_icon_payment_image_']";

        String clickTarget = isShipping
                ? scrollSelector
                : "#alt_icon_payment_image_2";

        basicPage.jsScrollAndClick($(scrollSelector));
        $(clickTarget).click();
        basicPage.alert_DeleteImage();

        if (isShipping) {
            basicPage.button_Save.click();
        } else {
            basicPage.button_SavePopUpWindow.click();
        }
    }
}