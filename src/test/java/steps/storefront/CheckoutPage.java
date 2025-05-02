package steps.storefront;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class CheckoutPage {
    SelenideElement button_PromoCode_Add = $("a[id^='sw_dropdown']");
    SelenideElement field_PromoCode = $(".ty-gift-certificate-coupon #coupon_field");
    SelenideElement button_PromoCode_Apply = $(".ty-gift-certificate-coupon .ty-btn-go");
    SelenideElement field_Email = $("#litecheckout_email");
    SelenideElement field_Address = $("#litecheckout_s_address");
    SelenideElement field_Zipcode = $("#litecheckout_s_zipcode");
    SelenideElement field_Country = $("#litecheckout_country");
    SelenideElement field_City = $(".litecheckout__input--selectable--like-field");
    SelenideElement agreement_TermsAndCondition = $("input[id^='id_accept_terms']");
    SelenideElement agreement_CheckoutPlaceOrder = $("input[id^='gdpr_agreements_checkout_place_order_']");
    SelenideElement agreement_CsCart = $("input[id^='product_agreements_']");
    SelenideElement button_PlaceOrder = $(".litecheckout__submit-btn");


    public void addPromoCode(String promoCode) {
        button_PromoCode_Add.click();
        field_PromoCode.click();
        field_PromoCode.sendKeys(promoCode);
        button_PromoCode_Apply.click();
        UtilsStorefront.waitForSpinnerDisappear();
        $(".cm-notification-close").shouldBe(Condition.visible);
    }

    public void fillMandatoryFields() {
        UtilsStorefront.jsScrollAndClick(field_Email);
        field_Email.setValue("mail@ukr.net");
        UtilsStorefront.jsScrollAndClick(field_Address);
        field_Address.setValue("Кубанская 21");
        UtilsStorefront.jsScrollAndClick(field_Zipcode);
        field_Zipcode.setValue("101000");
    }

    public void selectCountryAndCity(String country, String city) {
        UtilsStorefront.jsScrollAndClick(field_Country);
        field_Country.selectOption(country);
        UtilsStorefront.jsScrollAndClick(field_City);
        field_City.setValue(city);
        UtilsStorefront.jsScrollAndClick($(".litecheckout__overlay--active"));
        $(".litecheckout__overlay--active").shouldBe(Condition.disappear);
    }

    public void acceptTermsAndConditions() {
        if (agreement_TermsAndCondition.exists())
            agreement_TermsAndCondition.click();
    }

    public void acceptCheckoutPlaceOrderAgreement() {
        if (agreement_CheckoutPlaceOrder.exists())
            agreement_CheckoutPlaceOrder.click();
    }

    public void acceptCsCartAgreement() {
        if (agreement_CsCart.exists())
            agreement_CsCart.click();
    }

    public void placeOrder() {
        button_PlaceOrder.click();
        UtilsStorefront.waitForSpinnerDisappear();
        sleep(2000);
    }
}