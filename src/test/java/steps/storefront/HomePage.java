package steps.storefront;

import hooks.AssertUniqueIDOnPage;
import io.cucumber.java.en.Given;
import steps.adminPanel.BasicPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class HomePage implements AssertUniqueIDOnPage {
    public HomePage(){super();}

    private final SelenideElement button_CloseAdminBottomPanel = $("#bp_off_bottom_panel.bp-close");
    public SelenideElement cookie = $(".cm-btn-success");
    public SelenideElement header_MyAccount = $(".ut2-top-my-account .ut2-icon-outline-account-circle");
    public SelenideElement header_Cart = $(".ut2-top-cart-content .ty-hand i");
    public SelenideElement button_LogOut = $(".ut2-top-my-account a[href*='dispatch=auth.logout']");
    public SelenideElement button_SignIn = $(".ut2-top-my-account a[href*='login']");
    public SelenideElement button_SignIn_Popup = $("button[name='dispatch[auth.login]']");
    public SelenideElement button_MainMenuCategories = $(".top-menu-grid-vetrtical .ut2-icon");
    public SelenideElement button_AddToCart_PopUp = $(".buttons-container-picker .ty-btn__add-to-cart");
    public SelenideElement button_ContinueShopping = $(".ty-btn__secondary.cm-notification-close");
    public SelenideElement button_Checkout = $(".cm-cart-buttons a[href*='checkout']");

    @And("Переключаемся на {string} язык интерфейса витрины")
    public void selectLanguage(String lang_RuEnAr) {
        if(button_CloseAdminBottomPanel.isDisplayed()) {
            button_CloseAdminBottomPanel.click();
        }
        cookie.click();
        UtilsStorefront.safeCloseNotifications();
        $("a[id*='_wrap_language_']").hover().click();
        $(".ty-select-block__list-item a[data-ca-name='" + lang_RuEnAr + "']").click();
    }

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

    @And("Переходим на страницу категории {string} {string} \\(проверяем на уникальность ID)")
    public void navigateTo_CategoryPage(String mainCategory, String subCategory) {
        button_MainMenuCategories.click();
        $(".ut2-menu__item.ty-menu-item__" + mainCategory).hover();
        $x("//li[contains(@class, 'ty-menu-item__" + mainCategory + "')]//a[contains(@href, '" + subCategory + "/')]").click();
        assertUniqueIDOnPage();
    }

    @And("Добавляем товар с опциями в корзину")
    public void addProductWithOptions() {
        $(".ut2-btn__options").hover().click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        $("input[id^='option_svw']").click();   //Ставим чекбокс у опции товара
        button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        button_ContinueShopping.click();
    }

    @And("Добавляем товар с вариациями в корзину")
    public void addProductWithVariations() {
        $("a[id^='opener_ut2_select_variation']").scrollIntoCenter().click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        button_ContinueShopping.click();
    }

    @And("Переходим на страницу чекаута \\(проверяем на уникальность ID)")
    public void navigateTo_CheckoutPage() {
        executeJavaScript("window.scrollTo(0, 0);");
        header_Cart.click();
        button_Checkout.click();
        assertUniqueIDOnPage();
    }


    //Мобильное устройство
    SelenideElement flyMenu_button = $(".ut2-icon-outline-menu");
    SelenideElement flyMenu_Logout = $("a[href*='dispatch=auth.logout']");
    SelenideElement flyMenu_button_ViewDetails_SecondLevel = $(".ut2-lsl.active .ty-float-right");
    SelenideElement bottomPopUp_AddToCart = $(".buttons-container .ty-btn__add-to-cart");
    SelenideElement bottomPopUp_Checkout = $(".ty-product-notification__buttons a[href*='checkout']");

    @Given("Разавторизоваться на витрине")
    public void logoutOnStorefront() {
        UtilsStorefront.safeCloseNotifications();
        flyMenu_button.click();
        executeJavaScript("arguments[0].click();", flyMenu_Logout);
    }

    @And("Переходим на страницу категории {string} {string} \\(mobile)")
    public void navigateTo_CategoryPage__mobile(String mainCategory, String subCategory) {
        flyMenu_button.click();
        $(".ut2-lfl.ty-menu-item__" + mainCategory + " strong").click();
        $x("//strong[text()='" + subCategory + "']").click();
        flyMenu_button_ViewDetails_SecondLevel.click();
    }

    @And("Добавляем товар с опциями в корзину и переходим на страницу чекаута")
    public void addProductWithOptions__mobile() {
        $(".ut2-btn__options").scrollIntoCenter().click();
        sleep(1000);
        $("input[id^='option_svw']").click();   //Ставим чекбокс у опции товара
        sleep(2000);
        bottomPopUp_AddToCart.click();
        $(".notification-body-extended").shouldBe(Condition.visible, Duration.ofSeconds(10));
        bottomPopUp_Checkout.click();
    }
}