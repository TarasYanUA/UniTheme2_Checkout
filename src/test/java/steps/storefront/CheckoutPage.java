package steps.storefront;

import hooks.AssertUniqueIDOnPage;
import hooks.CollectAssertMessages;
import io.cucumber.java.en.Then;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;

import static com.codeborne.selenide.Selenide.*;
import org.assertj.core.api.SoftAssertions;

import java.time.Duration;

public class CheckoutPage implements AssertUniqueIDOnPage {
    public CheckoutPage(){super();}
    SoftAssertions softAssertions = CollectAssertMessages.getSoftAssertions();

    public SelenideElement button_PromoCode_Add = $("a[id^='sw_dropdown']");
    public SelenideElement field_PromoCode = $(".ty-gift-certificate-coupon #coupon_field");
    public SelenideElement button_PromoCode_Apply = $(".ty-gift-certificate-coupon .ty-btn-go");
    public SelenideElement selectShippingMethod = $(".b--ship-way__opted__text.b--pay-ship__opted__text");
    public SelenideElement field_Country = $("#litecheckout_country");
    public SelenideElement field_City = $(".litecheckout__input--selectable--like-field");
    public SelenideElement field_PaymentMethod = $(".b--pay-way__opted__text__title.b--pay-ship__opted__text__title");
    public SelenideElement agreement_TermsAndCondition = $("input[id^='id_accept_terms']");
    public SelenideElement agreement_CheckoutPlaceOrder = $("input[id^='gdpr_agreements_checkout_place_order_']");
    public SelenideElement agreement_CsCart = $("input[id^='product_agreements_']");
    public SelenideElement button_PlaceOrder = $(".litecheckout__submit-btn");

    @And("Используем промокод {string} \\(проверяем, что отобразился блок с применённой акцией)")
    public void usePromoCode(String promoCode) {
        button_PromoCode_Add.click();
        field_PromoCode.click();
        field_PromoCode.sendKeys(promoCode);
        button_PromoCode_Apply.click();
        HomePage.notification_close.shouldBe(Condition.visible, Duration.ofSeconds(5));

        softAssertions.assertThat($(".ty-coupons__item a[href]").exists())
                .as("Промокод не применился или отсутствует секция с указанием применённого промокода!")
                .isTrue();
    }

    @And("Заполняем обязательные поля для неавторизованных пользователей")
    public void fillMandatoryFields() {
        executeJavaScript("var element = document.querySelector('#litecheckout_email');element.click();");
        executeJavaScript("document.querySelector('#litecheckout_email').value = 'mail@ukr.net';");
        executeJavaScript("var element = document.querySelector('#litecheckout_s_address');element.click();");
        executeJavaScript("document.querySelector('#litecheckout_s_address').value = 'Кубанская 21';");
        executeJavaScript("var element = document.querySelector('#litecheckout_s_zipcode');element.click();");
        executeJavaScript("document.querySelector('#litecheckout_s_zipcode').value = '101000';");
    }

    @And("Выбираем страну и город доставки: {string}, {string}")
    public void selectCountryAndCity(String country, String city) {
        field_Country.click();
        field_Country.selectOption(country);
        field_City.click();
        field_City.clear();
        field_City.sendKeys(city);
        $(".litecheckout__overlay--active").click();
        $(".litecheckout__overlay--active").shouldBe(Condition.disappear);
    }

    @And("Выбираем способ доставки {string} из выпадающего списка и выбираем пункт выдачи \\(скриншот {string})")
    public void selectShippingMethod_asDropDownList(String shippingMethod, String screenshot) {
        if(!$x("//div[contains(@class, 'b--ship-way__opted__text__title')][contains(text(), '" + shippingMethod + "')]").exists()) {
            selectShippingMethod.click();
            screenshot(screenshot + " ShippingMethod DropdownList");
            $x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '" + shippingMethod + "')]").click();
        }
        $x("(//label[contains(@for, 'store_')])[3]").click();
        $x("//label[contains(@for, 'store_')]/input[@checked=\"checked\"]").shouldBe(Condition.exist);
        screenshot(screenshot + " ShippingMethod");
    }

    @And("Выбираем способ доставки {string} из обычного списка и выбираем пункт выдачи \\(скриншот {string})")
    public void selectShippingMethod_asSimpleList(String shippingMethod, String screenshot) {
        if(!$x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '"
                + shippingMethod + "')]/../../.. /../div[contains(@class, 'b--ship-way__unit_active')]").exists()){
            $x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '" + shippingMethod + "')]").click();
        }
        $x("(//label[contains(@for, 'store_')])[3]").click();
        sleep(2000);
        screenshot(screenshot + " ShippingMethod");
    }

    @And("Выбираем способ доставки {string} для первого продавца из выпадающего списка")
    public void selectShippingMethodForFirstVendor_DropdownList(String shippingMethod) {
        if(!$x("//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '"
                + shippingMethod + "')]").exists()){
            $(".b--ship-way__vendor-_0 .b--pay-ship__select").click();
            $x("//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(text(), '" + shippingMethod + "')]").click();
        }
    }

    @And("Выбираем способ доставки {string} для первого продавца из обычного списка")
    public void selectShippingMethodForFirstVendor_SimpleList(String shippingMethod) {
        if(!$x("//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '"
                + shippingMethod + "')]").exists()){
            $(".b--ship-way__vendor-_0 .b--pay-ship__select").click();
            $x("//div[contains(@class, 'b--ship-way__vendor-_0')]//div[contains(text(), '" + shippingMethod + "')]").click();
        }
    }

    @And("Выбираем способ оплаты {string} из выпадающего списка \\(скриншот {string})")
    public void selectPaymentMethod_asDropDownList(String paymentMethod, String screenshot) {
        if(!$x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").isDisplayed()) {
            field_PaymentMethod.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}");
            field_PaymentMethod.click();
            $x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").hover();
            screenshot(screenshot + " PaymentMethod DropdownList");
            $x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").click();
            sleep(2000);
            screenshot(screenshot + " PaymentMethod");
        }
    }

    @And("Выбираем способ оплаты {string} из обычного списка \\(скриншот {string})")
    public void selectPaymentMethod_asSimpleList(String paymentMethod, String screenshot) {
        if(!$x("//div[contains(@class, 'b--ship-way__unit_active')]//div[contains(text(), '" + paymentMethod + "')]").exists()) {
            $x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").click();
            sleep(2000);
            screenshot(screenshot + " PaymentMethod");
        }
    }

    @And("Ставим соглашения \\(проверяем на уникальность ID)")
    public void checkAgreements() {
        agreement_TermsAndCondition.click();
        if(agreement_CheckoutPlaceOrder.exists())
            agreement_CheckoutPlaceOrder.click();
        if(agreement_CsCart.exists())
            agreement_CsCart.click();
        assertUniqueIDOnPage();
    }

    @Then("Завершаем оформление заказа и проверяем, что мы на странице {string} \\(скриншот {string})")
    public void placeOrder(String pageBreadcrumb, String screenshot) {
        button_PlaceOrder.click();
        sleep(2000);
        screenshot(screenshot + " Success");

        softAssertions.assertThat($x("//bdi[text()='" + pageBreadcrumb + "']").exists())
                .as("Заказ не оформлен успешно!")
                .isTrue();
        assertUniqueIDOnPage();
    }

    @And("Выбираем способ доставки {string} из выпадающего списка и выбираем пункт выдачи \\(скриншот {string}) \\(mobile)")
    public void selectShippingMethod_asDropDownList__mobile(String shippingMethod, String screenshot) {
        if(!$x("//div[contains(@class, 'b--ship-way__opted__text__title')][contains(text(), '" + shippingMethod + "')]").exists()) {
            selectShippingMethod.click();
            screenshot(screenshot + " ShippingMethod DropdownList");
            $x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '" + shippingMethod + "')]").click();
        }
        //нужно будет дописать ещё 2 строки $x("(//label[contains(@for, 'store_')])[3]").click(); и sleep(2000); + кнопка нажатия для открытия окна выбора способа доставки
        //когда будет исправлена ошибка в теме https://abteam.planfix.com/task/48376
        screenshot(screenshot + " ShippingMethod");
    }

    @And("Выбираем способ доставки {string} из обычного списка и выбираем пункт выдачи \\(скриншот {string}) \\(mobile)")
    public void selectShippingMethod_asSimpleList__mobile(String shippingMethod, String screenshot) {
        if(!$x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '"
                + shippingMethod + "')]/../../../../div[contains(@class, 'b--ship-way__unit_active')]").exists()){
            $x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '" + shippingMethod + "')]").click();
        }
        //нужно будет дописать ещё 2 строки $x("(//label[contains(@for, 'store_')])[3]").click(); и sleep(2000); + кнопка нажатия для открытия окна выбора способа доставки
        //когда будет исправлена ошибка в теме https://abteam.planfix.com/task/48376
        sleep(2000);
        screenshot(screenshot + " ShippingMethod");
    }
}