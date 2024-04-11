package steps;

import adminPanel.csCartPages.BasicPage;
import adminPanel.csCartPages.LayoutPage;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import storefront.CheckoutPage;
import storefront.HomePage;

import static com.codeborne.selenide.Selenide.*;

public class DropdownList_Ult_Test {
    BasicPage basicPage = new BasicPage();
    LayoutPage layoutPage = new LayoutPage();
    HomePage homePage = new HomePage();
    CheckoutPage checkoutPage = new CheckoutPage();
    SoftAssertions softAssertions = new SoftAssertions();

    @Given("Устанавливаем макет Light v2 по умолчанию")
    public void setLayout_Light_asDefault(){
        /*LayoutPage layoutPage = basicPage.navigateTo_LayoutPage();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();*/
    }
    @And("Настраиваем блок {string} в виде выпадающего списка")
    public void setBlockAsDropDownList(String blockName) {
       /* layoutPage.layoutTab_Checkout.click();
        layoutPage.navigateToBlockSettings(blockName);
        layoutPage.button_SettingsOfTemplate.click();
        if(!layoutPage.checkbox_DisplayAsDropDownList.isSelected())
            layoutPage.checkbox_DisplayAsDropDownList.click();
        layoutPage.button_SaveBlockProperties.click();*/
    }

    @When("Переходим на витрину")
    public void navigateToStorefrontMainPage() {
        basicPage.goTo_Storefront.scrollTo().click();
        basicPage.focusBrowserTab(1);
        homePage.cookie.click();
        sleep(1500);
        if(homePage.notification_close.exists())
            homePage.notification_close.click();
        basicPage.selectLanguage("ru");
    }

    @And("Авторизуемся на сайте \\(проверяем на уникальность ID)")
    public void authorizeOnStorefront() {
        /*homePage.header_MyAccount.click();
        if(!homePage.button_LogOut.exists()){
            homePage.button_SignIn.click();
            BasicPage.popupWindow.shouldBe(Condition.visible);
            homePage.button_SignIn_Popup.click();
        }
        basicPage.assertUniqueIDOnPage();*/
    }
    @And("Переходим на страницу категории {string} {string} \\(проверяем на уникальность ID)")
    public void navigateTo_CategoryPage(String mainCategory, String subCategory) {
        /*homePage.button_MainMenuCategories.click();
        $("li[class='ty-menu__item cm-menu-item-responsive first-lvl ty-menu-item__" + mainCategory + "']").hover();
        $x("//li[contains(@class, 'ty-menu-item__" + mainCategory + "')]//a[contains(@href, '" + subCategory + "/')]").click();
        basicPage.assertUniqueIDOnPage();*/
    }
    @And("Добавляем товар с опциями в корзину")
    public void addProductWithOptions() {
       /* $(".ut2-btn__options").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        $("input[id^='option_svw']").click();   //Ставим чекбокс у опции товара
        homePage.button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        homePage.button_ContinueShopping.click();*/
    }
    @And("Добавляем товар с вариациями в корзину")
    public void addProductWithVariations() {
        /*$("a[id^='opener_ut2_select_variation']").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
        homePage.button_AddToCart_PopUp.click();
        $(".notification-body-extended").shouldBe(Condition.visible);
        homePage.button_ContinueShopping.click();*/
    }
    @And("Переходим на страницу чекаута \\(проверяем на уникальность ID)")
    public void navigateTo_CheckoutPage() {
        homePage.header_Cart.click();
        homePage.button_Checkout.click();
        basicPage.assertUniqueIDOnPage();
    }
    @And("Используем промокод {string} \\(проверяем, что отобразился блок с применённой акцией)")
    public void usePromoCode(String promoCode) {
        checkoutPage.button_PromoCode_Add.click();
        checkoutPage.field_PromoCode.click();
        checkoutPage.field_PromoCode.sendKeys(promoCode);
        checkoutPage.button_PromoCode_Apply.click();
        homePage.notification_close.shouldBe(Condition.visible);
        softAssertions.assertThat($(".ty-coupons__item a[href]").isDisplayed())
                .as("Промокод не применился или отсутствует секция с указанием применённого промокода!").isTrue();
    }

    @And("Выбираем способ доставки: {string}, {string}, {string} и выбираем пункт выдачи")
    public void selectShippingMethod(String country, String city, String shippingMethod) {
        checkoutPage.field_Country.click();
        checkoutPage.field_Country.selectOption(country);
        checkoutPage.field_City.click();
        checkoutPage.field_City.clear();
        $x("//div[@id='litecheckout_autocomplete_dropdown']//div[contains(text(), '" + city + "')]").click();
        if(!$x("//div[contains(@class, 'b--ship-way__opted__text__title')][contains(text(), '" + shippingMethod + "')]").isDisplayed()) {
            checkoutPage.selectShippingMethod.click();
            $x("//div[contains(@class, 'b--ship-way__unit__text')]/div[contains(text(), '" + shippingMethod + "')]").click();
        }
        $x("(//label[contains(@for, 'store_')])[3]").click();
        $x("//label[contains(@for, 'store_')]/input[@checked=\"checked\"]").shouldBe(Condition.exist);
        basicPage.assertUniqueIDOnPage();

        screenshot("CheckoutPage");
    }
}