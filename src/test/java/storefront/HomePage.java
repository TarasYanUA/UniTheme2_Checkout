package storefront;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    public HomePage(){super();}

    public SelenideElement cookie = $(".cm-btn-success");
    public static SelenideElement notification_close = $(".cm-notification-close");
    public SelenideElement header_MyAccount = $(".ut2-top-my-account .ut2-icon-outline-account-circle");
    public SelenideElement button_LogOut = $(".ut2-top-my-account a[href*='dispatch=auth.logout']");
    public SelenideElement button_SignIn = $(".ut2-top-my-account a[href*='login']");
    public SelenideElement button_SignIn_Popup = $("button[name='dispatch[auth.login]']");
    public SelenideElement button_MainMenuCategories = $(".top-menu-grid-vetrtical .ut2-icon");
    public SelenideElement button_AddToCart_PopUp = $(".buttons-container-picker .ty-btn__add-to-cart");
    public SelenideElement button_ContinueShopping = $(".ty-btn__secondary.cm-notification-close");
    public SelenideElement header_Cart = $(".ut2-top-cart-content .ty-hand");
    public SelenideElement button_Checkout = $(".cm-cart-buttons a[href$='checkout-ru/']");
}