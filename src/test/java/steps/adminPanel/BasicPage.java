package steps.adminPanel;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Alert;

public class BasicPage {

    SelenideElement popupWindow = $(".ui-dialog-title");
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


    // Мобильное устройство
    public void navigateTo_LayoutPage__mobile() {
        mobile_MainMenu.click();
        mobile_section_Themes.click();
        mobile_ThemeActionsMenu.click();
        mobile_sectionLayouts.click();
    }
}