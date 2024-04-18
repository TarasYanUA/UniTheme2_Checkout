package steps.storefront;

import hooks.AssertUniqueIDOnPage;
import steps.adminPanel.csCartPages.BasicPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;

import static com.codeborne.selenide.Selenide.*;

public class HomePage implements AssertUniqueIDOnPage {
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

    @And("Авторизуемся на сайте \\(проверяем на уникальность ID)")
    public void authorizeOnStorefront() {
        header_MyAccount.click();
        if(!button_LogOut.exists()){
            button_SignIn.click();
            BasicPage.popupWindow.shouldBe(Condition.visible);
            button_SignIn_Popup.click();
        }
        assertUniqueIDOnPage();
    }
    @And("Выходим из личного кабинета \\(проверяем на уникальность ID)")
    public void unAuthorizeOnStorefront() {
        header_MyAccount.click();
        if(button_LogOut.exists()){
            button_LogOut.click();
        }
        assertUniqueIDOnPage();
    }
    @And("Переключаемся на {string} язык интерфейса витрины")
    public void selectLanguage(String lang_RuEnAr) {
        cookie.click();
        sleep(1000);
        if(notification_close.exists())
            notification_close.click();
        $("a[id*='_wrap_language_']").hover().click();
        $(".ty-select-block__list-item a[data-ca-name='" + lang_RuEnAr + "']").click();
    }
    @And("Переходим на страницу категории {string} {string} \\(проверяем на уникальность ID)")
    public void navigateTo_CategoryPage(String mainCategory, String subCategory) {
        button_MainMenuCategories.click();
        $("li[class='ty-menu__item cm-menu-item-responsive first-lvl ty-menu-item__" + mainCategory + "']").hover();
        $x("//li[contains(@class, 'ty-menu-item__" + mainCategory + "')]//a[contains(@href, '" + subCategory + "/')]").click();
        assertUniqueIDOnPage();
    }
    @And("Добавляем товар с опциями в корзину")
    public void addProductWithOptions() {
        $(".ut2-btn__options").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        $("input[id^='option_svw']").click();   //Ставим чекбокс у опции товара
        button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        button_ContinueShopping.click();
    }
    @And("Добавляем товар с вариациями в корзину")
    public void addProductWithVariations() {
        $("a[id^='opener_ut2_select_variation']").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        button_ContinueShopping.click();
    }
    @And("Переходим на страницу чекаута \\(проверяем на уникальность ID)")
    public void navigateTo_CheckoutPage() {
        header_Cart.click();
        button_Checkout.click();
        assertUniqueIDOnPage();
    }
}