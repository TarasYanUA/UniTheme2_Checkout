package steps.storefront;

import steps.adminPanel.csCartPages.BasicPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutPage {
    public CheckoutPage(){super();}

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

    HomePage homePage = new HomePage();
    SoftAssertions softAssertions = new SoftAssertions();

    public void addProductWithOptions() {
        $(".ut2-btn__options").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        $("input[id^='option_svw']").click();   //Ставим чекбокс у опции товара
        homePage.button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        homePage.button_ContinueShopping.click();
    }
    public void addProductWithVariations() {
        $("a[id^='opener_ut2_select_variation']").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        homePage.button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        homePage.button_ContinueShopping.click();
    }
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
    }
    public void selectPaymentMethod(String paymentMethod) {
        if(!$x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").isDisplayed()) {
            field_PaymentMethod.click();
            $x("//div[@class='litecheckout__shipping-method__title'][contains(text(), '" + paymentMethod + "')]").click();
            sleep(2000);
        }
    }
    public void checkAgreements() {
        agreement_TermsAndCondition.click();
        if(agreement_CheckoutPlaceOrder.isDisplayed())
            agreement_CheckoutPlaceOrder.click();
    }
}