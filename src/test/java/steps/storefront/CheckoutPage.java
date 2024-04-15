package steps.storefront;

import hooks.AssertUniqueIDOnPage;
import io.cucumber.java.en.Then;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutPage implements AssertUniqueIDOnPage {
    public CheckoutPage(){super();}

    SoftAssertions softAssertions = new SoftAssertions();

    public SelenideElement button_PromoCode_Add = $("a[id^='sw_dropdown']");
    public SelenideElement field_PromoCode = $(".ty-gift-certificate-coupon #coupon_field");
    public SelenideElement button_PromoCode_Apply = $(".ty-gift-certificate-coupon .ty-btn-go");
    public SelenideElement selectShippingMethod = $(".b--ship-way__opted__text.b--pay-ship__opted__text");
    public SelenideElement field_Country = $("#litecheckout_country");
    public SelenideElement field_City = $(".litecheckout__input--selectable--like-field");
    public SelenideElement field_PaymentMethod = $(".b--pay-way__opted__text__title.b--pay-ship__opted__text__title");
    public SelenideElement agreement_TermsAndCondition = $("input[id^='id_accept_terms']");
    public SelenideElement agreement_CheckoutPlaceOrder = $("input[id^='gdpr_agreements_checkout_place_order_']");
    public SelenideElement button_PlaceOrder = $(".litecheckout__submit-btn");

    @And("Используем промокод {string} \\(проверяем, что отобразился блок с применённой акцией)")
    public void usePromoCode(String promoCode) {
        button_PromoCode_Add.click();
        field_PromoCode.click();
        field_PromoCode.sendKeys(promoCode);
        button_PromoCode_Apply.click();
        HomePage.notification_close.shouldBe(Condition.visible);
        softAssertions.assertThat($(".ty-coupons__item a[href]").isDisplayed())
                .as("Промокод не применился или отсутствует секция с указанием применённого промокода!");
    }
    @And("Выбираем способ доставки: {string}, {string}, {string} и выбираем пункт выдачи")
    public void selectShippingMethod(String country, String city, String shippingMethod) {
        field_Country.click();
        field_Country.selectOption(country);
        field_City.click();
        field_City.clear();
        field_City.sendKeys(city);
        $(".litecheckout__overlay--active").click();
        if(!$x("//div[contains(@class, 'b--ship-way__opted__text__title')][contains(text(), '" + shippingMethod + "')]").isDisplayed()) {
            selectShippingMethod.click();
            $x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '" + shippingMethod + "')]").click();
        }
        $x("(//label[contains(@for, 'store_')])[3]").click();
        $x("//label[contains(@for, 'store_')]/input[@checked=\"checked\"]").shouldBe(Condition.exist);
        screenshot("CheckoutPage " + System.currentTimeMillis());
    }
    @And("Выбираем способ оплаты {string}")
    public void selectPaymentMethod(String paymentMethod) {
        if(!$x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").isDisplayed()) {
            field_PaymentMethod.click();
            $x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").click();
            sleep(2000);
        }
    }
    @And("Ставим соглашения \\(проверяем на уникальность ID)")
    public void checkAgreements() {
        agreement_TermsAndCondition.click();
        if(agreement_CheckoutPlaceOrder.isDisplayed())
            agreement_CheckoutPlaceOrder.click();
        assertUniqueIDOnPage();
    }
    @Then("Завершаем оформление заказа и проверяем, что мы на странице {string}")
    public void placeOrder(String pageBreadcrumb) {
        button_PlaceOrder.click();
        softAssertions.assertThat($x("//bdi[text()='" + pageBreadcrumb + "']").exists())
                .as("Заказ не оформлен успешно!");
        sleep(2000);
        assertUniqueIDOnPage();
    }
}