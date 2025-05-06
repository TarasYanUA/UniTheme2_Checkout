package steps.storefront;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import hooks.AssertUniqueIDOnPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePageMobile implements AssertUniqueIDOnPage {

    public HomePageMobile() { super(); }

    SelenideElement flyMenu_button = $(".ut2-icon-outline-menu");
    SelenideElement flyMenu_Logout = $("a[href*='dispatch=auth.logout']");
    SelenideElement flyMenu_button_ViewDetails_SecondLevel = $(".ut2-lsl.active .ty-float-right");
    SelenideElement bottomPopUp_AddToCart = $(".buttons-container .ty-btn__add-to-cart");
    SelenideElement bottomPopUp_Checkout = $(".ty-product-notification__buttons a[href*='checkout']");


    public void logoutMobile() {
        UtilsStorefront.safeCloseNotifications();
        flyMenu_button.click();
        UtilsStorefront.jsScrollAndClick(flyMenu_Logout);
    }

    public void navigateToCategoryMobile(String mainCategory, String subCategory) {
        flyMenu_button.click();
        $(".ut2-lfl.ty-menu-item__" + mainCategory + " strong").click();
        $x("//strong[text()='" + subCategory + "']").click();
        flyMenu_button_ViewDetails_SecondLevel.click();
    }

    public void addProductWithOptionsAndGoToCheckoutMobile() {
        UtilsStorefront.jsScrollAndClick($(".ut2-btn__options"));
        $("input[id^='option_svw']").shouldBe(Condition.visible).click();
        UtilsStorefront.waitForSpinnerDisappear();
        bottomPopUp_AddToCart.click();
        $(".notification-body-extended").shouldBe(Condition.visible, Duration.ofSeconds(10));
        bottomPopUp_Checkout.click();
    }
}
