package steps.storefront;

import hooks.AssertUniqueIDOnPage;
import steps.adminPanel.BasicPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage implements AssertUniqueIDOnPage {

    SelenideElement header_Cart = $(".ut2-top-cart-content .ty-hand i");
    SelenideElement button_LogOut = $(".ut2-top-my-account a[href*='dispatch=auth.logout']");
    SelenideElement button_SignIn = $(".ut2-top-my-account a[href*='login']");
    SelenideElement button_SignIn_Popup = $("button[name='dispatch[auth.login]']");
    SelenideElement button_MainMenuCategories = $(".top-menu-grid-vetrtical .ut2-icon");
    SelenideElement button_AddToCart_PopUp = $(".buttons-container-picker .ty-btn__add-to-cart");
    SelenideElement button_ContinueShopping = $(".ty-btn__secondary.cm-notification-close");
    SelenideElement button_Checkout = $(".cm-cart-buttons a[href*='checkout']");

    
    public void selectLanguage(String langCode) {
        UtilsStorefront.handleCookieAndAdminPanel();
        $("a[id*='_wrap_language_']").hover().click();
        $(".ty-select-block__list-item a[data-ca-name='" + langCode + "']").click();
    }

    public void authorize() {
        UtilsStorefront.openMyAccount();
        if (!button_LogOut.exists()) {
            button_SignIn.click();
            BasicPage.popupWindow.shouldBe(Condition.visible);
            button_SignIn_Popup.click();
        }
    }

    public void logout() {
        UtilsStorefront.openMyAccount();
        if (button_LogOut.exists()) {
            button_LogOut.click();
        }
    }

    public void navigateToCategory(String mainCategory, String subCategory) {
        button_MainMenuCategories.click();
        $(".ut2-menu__item.ty-menu-item__" + mainCategory).hover();
        $x("//li[contains(@class, 'ty-menu-item__" + mainCategory + "')]//a[contains(@href, '" + subCategory + "/')]").click();
    }

    public void addProductWithOptions() {
        $(".ut2-btn__options").hover().click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        $("input[id^='option_svw']").click();
        button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        button_ContinueShopping.click();
    }

    public void addProductWithVariations() {
        UtilsStorefront.jsScrollAndClick($("a[id^='opener_ut2_select_variation']"));
        BasicPage.popupWindow.shouldBe(Condition.visible);
        button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        button_ContinueShopping.click();
    }

    public void goToCheckout() {
        UtilsStorefront.jsScrollToTop();
        UtilsStorefront.safeCloseNotifications();
        header_Cart.click();
        button_Checkout.click();
    }
}