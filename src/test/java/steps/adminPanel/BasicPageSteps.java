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

        if (methodType.equalsIgnoreCase("доставки")) {
            basicPage.section_ShippingMethod.click();
            $x("//div[@id='shippings_content']//a[text()='" + methodName + "']").click();
            sleep(2000);
            $x("//a[contains(@id, 'url_')]").click();
            basicPage.alert_AddImage("https://dummyimage.com/50x50/09f/fff.png&text=b2");
            basicPage.saveChanges();
        } else if (methodType.equalsIgnoreCase("оплаты")) {
            basicPage.section_PaymentMethod.click();
            $x("//a[text()='" + methodName + "']").click();
            basicPage.popupWindow.shouldBe(Condition.exist);
            $x("//span[text()='" + methodName + "']/../..//a[contains(@id, 'url_')]").click();
            basicPage.alert_AddImage("https://dummyimage.com/50x50/09f/fff.png&text=a1");
            basicPage.savePopUpWindow();
        } else {
            throw new IllegalArgumentException("Неизвестный тип: " + methodType);
        }
    }

    @And("Удаляем изображение способу {string} {string}")
    public void deleteImageFromMethod(String methodType, String methodName) {
        basicPage.openMenuOfSettings();

        if (methodType.equalsIgnoreCase("доставки")) {
            basicPage.section_ShippingMethod.click();
            $x("//div[@id='shippings_content']//a[text()='" + methodName + "']").click();
            basicPage.popupWindow.shouldBe(Condition.exist);
            basicPage.alert_DeleteImage();
            basicPage.saveChanges();
        } else if (methodType.equalsIgnoreCase("оплаты")) {
            basicPage.section_PaymentMethod.click();
            $x("//a[text()='" + methodName + "']").click();
            basicPage.popupWindow.shouldBe(Condition.exist);
            basicPage.alert_DeleteImage();
            basicPage.savePopUpWindow();
        } else {
            throw new IllegalArgumentException("Неизвестный тип: " + methodType);
        }
    }


    // Мобильное устройство
    @Given("Переходим на страницу \"Веб-сайт -- Темы -- Макеты\" \\(mobile)")
    public void navigateTo_LayoutPage__mobile() {
        basicPage.navigateTo_LayoutPage__mobile();
    }

    @And("Удаляем изображение способу {string} {string} \\(mobile)")
    public void deleteImageFromMethodMobile(String methodType, String methodName) {
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

        $x(xpath)
                .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                .click();

        basicPage.popupWindow.shouldBe(Condition.exist);

        String scrollSelector = isShipping
                ? "input[id*='alt_icon_shipping_']"
                : "input[id*='alt_icon_payment_image_']";

        String clickTarget = isShipping
                ? scrollSelector
                : "#alt_icon_payment_image_2";

        $(scrollSelector)
                .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                .click();

        $(clickTarget).click();

        basicPage.alert_DeleteImage();

        if (isShipping) {
            basicPage.button_Save.click();
        } else {
            basicPage.button_SavePopUpWindow.click();
        }
    }
}