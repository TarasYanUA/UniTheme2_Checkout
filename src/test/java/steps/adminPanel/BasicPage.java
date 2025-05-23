package steps.adminPanel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Alert;

public class BasicPage {

    public static SelenideElement popupWindow = $(".ui-dialog-title");
    SelenideElement button_Save = $(".btn.btn-primary.cm-submit");
    SelenideElement button_ShowAdminPanel = $(".bp-bottom-button--logo");
    SelenideElement goTo_Storefront = $(".bp-nav__item-text");
    SelenideElement menu_Website = $("a[href*='dispatch=themes.manage'].main-menu-1__link");
    SelenideElement section_Themes = $("#website_themes");
    SelenideElement sectionLayouts = $("a[href$='block_manager.manage']");
    SelenideElement menu_Settings = $("#administration");
    SelenideElement section_PaymentMethod = $("a[href$='payments.manage'].administration-page__block");
    SelenideElement section_ShippingMethod = $("a[href$='shippings.manage'].administration-page__block");
    SelenideElement button_SavePopUpWindow = $(".ui-dialog-content input[value='Сохранить']");

    // Мобильное устройство
    SelenideElement mobile_MainMenu = $(".mobile-menu-toggler");
    SelenideElement mobile_ThemeActionsMenu = $(".actions-menu__dropdown-toggle");
    SelenideElement mobile_section_Themes = $("a[href*='dispatch=themes.manage'].main-menu-1__link");
    SelenideElement mobile_sectionLayouts = $(".actions-menu__dropdown-item-wrapper a[href$='block_manager.manage']");


    public void navigateTo_LayoutPage() {
        menu_Website.click();
        section_Themes.click();
        sectionLayouts.click();
    }

    public void navigateToStorefront_HomePage() {
        button_ShowAdminPanel.click();
        goTo_Storefront.click();
    }
    
    public void saveChanges(){
        button_Save.click();
    }
    
    public void savePopUpWindow(){
        button_SavePopUpWindow.click();
    }
    
    public void openMenuOfSettings(){
        menu_Settings.click();
    }

    public void alert_AddImage(String imageUrl) {
        Alert alert = webdriver().driver().switchTo().alert();
        sleep(1500);
        alert.sendKeys(imageUrl);
        alert.accept();
    }

    public void alert_DeleteImage() {
        if ($(".image-delete").exists()) {
            $(".image-delete").parent().hover();
            $(".image-delete").click();
            Alert alert = webdriver().driver().switchTo().alert();
            sleep(1500);
            alert.accept();
        }
    }

    public void jsScrollAndClick(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", element);
    }

    public void openShippingMethodSettings(String methodName) {
        section_ShippingMethod.click();
        $x("//div[@id='shippings_content']//a[text()='" + methodName + "']").click();
        sleep(2000);    //здесь нужна именно пауза
    }

    public void openPaymentMethodSettings(String methodName) {
        section_PaymentMethod.click();
        $x("//a[text()='" + methodName + "']").click();
        popupWindow.shouldBe(Condition.exist);
    }

    public void addImageToShippingMethod(String methodName, String imageUrl) {
        openShippingMethodSettings(methodName);
        $x("//a[contains(@id, 'url_')]").click();
        alert_AddImage(imageUrl);
        saveChanges();
    }

    public void addImageToPaymentMethod(String methodName, String imageUrl) {
        openPaymentMethodSettings(methodName);
        $x("//span[text()='" + methodName + "']/../..//a[contains(@id, 'url_')]").click();
        alert_AddImage(imageUrl);
        savePopUpWindow();
    }

    public void deleteImageFromShippingMethod(String methodName) {
        openShippingMethodSettings(methodName);
        alert_DeleteImage();
        saveChanges();
    }

    public void deleteImageFromPaymentMethod(String methodName) {
        openPaymentMethodSettings(methodName);
        alert_DeleteImage();
        savePopUpWindow();
    }



    // Мобильное устройство
    public void navigateTo_LayoutPage__mobile() {
        mobile_MainMenu.click();
        mobile_section_Themes.click();
        mobile_ThemeActionsMenu.click();
        mobile_sectionLayouts.click();
    }
}