package steps.storefront;

import com.codeborne.selenide.Condition;
import hooks.AssertUniqueIDOnPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutPageSteps implements AssertUniqueIDOnPage {

    public CheckoutPageSteps() { super(); }

    CheckoutPage checkoutPage = new CheckoutPage();
    SoftAssertions softAssertions = new SoftAssertions();

    @And("Используем промокод {string} \\(проверяем, что отобразился блок с применённой акцией)")
    public void usePromoCode(String promoCode) {
        checkoutPage.addPromoCode(promoCode);
        $(".cm-notification-close").shouldBe(Condition.visible);

        softAssertions.assertThat($(".ty-coupons__item a[href]").exists())
                .as("Промокод не применился или отсутствует секция с указанием применённого промокода!")
                .isTrue();
    }

    @And("Заполняем обязательные поля для неавторизованных пользователей")
    public void fillRequiredFieldsForUnauthenticatedUser() {
        checkoutPage.fillMandatoryFields();
    }

    @And("Выбираем страну и город доставки: {string}, {string}")
    public void selectCountryAndCity(String country, String city) {
        checkoutPage.selectCountryAndCity(country, city);
    }

    @And("Выбираем способ {string} {string} из обычного списка \\(скриншот {string})")
    public void selectMethodFromSimpleList(String methodType, String methodName, String screenshot) {
        String methodXpath = "";

        if (methodType.equals("доставки")) {
            methodXpath = "//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '{methodName}')]";
            // Выбираем пункт выдачи
            $$x("//label[contains(@for, 'store_')]").get(2).click();
            UtilsStorefront.waitForSpinnerDisappear();
        } else if (methodType.equals("оплаты")) {
            methodXpath = "//div[contains(@class, 'b--pay-way__unit__text b--pay-ship__unit__text')]//div[contains(text(), '{methodName}')]";
        }

        UtilsStorefront.selectMethodFromList(
                methodName,
                methodXpath,
                screenshot + " Способ " + methodType
        );
    }

    @And("Выбираем способ {string} {string} из выпадающего списка \\(скриншот {string})")
    public void selectMethodFromDropDown(String methodType, String methodName, String screenshot) {
        String methodXpath = "";
        String dropdownSelector = "";

        if (methodType.equals("доставки")) {
            methodXpath = "//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '{methodName}')]";
            dropdownSelector = ".b--ship-way__vendor-_0 .b--pay-ship__select";
            // Выбираем пункт выдачи
            $$x("//label[contains(@for, 'store_')]").get(2).click();
            UtilsStorefront.waitForSpinnerDisappear();
        } else if (methodType.equals("оплаты")) {
            methodXpath = "//div[@class='litecheckout__shipping-method__title'][contains(text(), '{methodName}')]";
            dropdownSelector = ".b--pay-way__opted__text__title.b--pay-ship__opted__text__title";
        }

        UtilsStorefront.selectMethodFromDropDown(
                methodName,
                methodXpath,
                dropdownSelector,
                screenshot + " Способ " + methodType
        );
    }

    @And("Выбираем способ доставки {string} для первого продавца из выпадающего списка")
    public void selectShippingMethodForFirstVendor_DropdownList(String shippingMethod, String screenshot) {
        UtilsStorefront.selectMethodFromDropDown(
                shippingMethod,
                "//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '{methodName}')]",
                ".b--ship-way__vendor-_0 .b--pay-ship__select",
                screenshot + " ShippingMethod"
        );
    }

    @And("Выбираем способ доставки {string} для первого продавца из обычного списка")
    public void selectShippingMethodForFirstVendor_SimpleList(String shippingMethod) {
        if (!$x("//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '"
                + shippingMethod + "')]").exists()) {
            $(".b--ship-way__vendor-_0 .b--pay-ship__select").click();
            $x("//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(text(), '" + shippingMethod + "')]").click();
            UtilsStorefront.waitForSpinnerDisappear();
        }
    }

    @And("Ставим соглашения \\(проверяем на уникальность ID)")
    public void acceptAgreements() {
        checkoutPage.acceptTermsAndConditions();
        checkoutPage.acceptCheckoutPlaceOrderAgreement();
        checkoutPage.acceptCsCartAgreement();
        assertUniqueIDOnPage();
    }

    @Then("Завершаем оформление заказа и проверяем, что заказ оформлен успешно \\(скриншот {string})")
    public void completeOrder(String screenshot) {
        checkoutPage.placeOrder();

        softAssertions.assertThat($(".ty-checkout-complete__order-success").exists())
                .as("Заказ не оформлен успешно!")
                .isTrue();
        screenshot(screenshot + " Success");
        assertUniqueIDOnPage();
    }
}