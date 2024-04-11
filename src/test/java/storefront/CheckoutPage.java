package storefront;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutPage {
    public CheckoutPage(){super();}

    public SelenideElement button_PromoCode_Add = $("a[id^='sw_dropdown']");
    public SelenideElement field_PromoCode = $(".ty-gift-certificate-coupon #coupon_field");
    public SelenideElement button_PromoCode_Apply = $(".ty-gift-certificate-coupon .ty-btn-go");
    public SelenideElement selectShippingMethod = $(".b--ship-way__opted__text.b--pay-ship__opted__text");
    public SelenideElement field_Country = $("#litecheckout_b_country");
    public SelenideElement field_City = $(".litecheckout__input--selectable--like-field");
}