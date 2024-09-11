package steps.adminPanel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Alert;

public class BasicPage {
    public BasicPage(){super();}

    public static SelenideElement popupWindow = $(".ui-dialog-title");
    private final SelenideElement button_Save = $(".btn.btn-primary.cm-submit");
    private final SelenideElement button_ShowAdminPanel = $(".bp-bottom-button--logo");
    private final SelenideElement goTo_Storefront = $(".bp-nav__item-text");
    private final SelenideElement menu_Website = $("a[href*='dispatch=themes.manage'].main-menu-1__link");
    private final SelenideElement section_Themes = $("#website_themes");
    private final SelenideElement sectionLayouts = $("a[href$='block_manager.manage']");
    private final SelenideElement menu_Settings = $("#administration");
    private final SelenideElement section_PaymentMethod = $("a[href$='payments.manage'].administration-page__block");
    private final SelenideElement button_SavePopUpWindow = $(".ui-dialog-content input[value='Сохранить']");
    private final SelenideElement section_ShippingMethod = $("a[href$='shippings.manage'].administration-page__block");

    @Given("Переходим на страницу \"Веб-сайт -- Темы -- Макеты\"")
    public void navigateTo_LayoutPage() {
        menu_Website.click();
        section_Themes.click();
        sectionLayouts.click();
    }

    @When("Переходим на витрину")
    public void navigateToStorefront_HomePage() {
        button_ShowAdminPanel.click();
        goTo_Storefront.click();
    }

    @And("Добавляем изображение способу оплаты {string}")
    public void addImageToPaymentMethod(String paymentMethod) {
        menu_Settings.click();
        section_PaymentMethod.click();
        $x("//a[text()='" + paymentMethod + "']").click();
        BasicPage.popupWindow.shouldBe(Condition.exist);
        $x("//span[text()='" + paymentMethod + "']/../..//a[contains(@id, 'url_')]").click();
        Alert alert = webdriver().driver().switchTo().alert();
        sleep(1500);
        alert.sendKeys("https://dummyimage.com/50x50/09f/fff.png&text=a1");
        alert.accept();
        button_SavePopUpWindow.click();
    }

    @And("Добавляем изображение способу доставки {string}")
    public void addImageToShippingMethod(String shippingMethod) {
        menu_Settings.click();
        section_ShippingMethod.click();
        $x("//div[@id='shippings_content']//a[text()='" + shippingMethod + "']").click();
        sleep(2000);
        $x("//a[contains(@id, 'url_')]").click();
        Alert alert = webdriver().driver().switchTo().alert();
        sleep(1500);
        alert.sendKeys("https://dummyimage.com/50x50/09f/fff.png&text=b2");
        alert.accept();
        button_Save.click();
    }

    @And("Удаляем изображение способу оплаты {string}")
    public void deleteImageToPaymentMethod(String paymentMethod) {
        menu_Settings.click();
        section_PaymentMethod.click();
        $x("//a[text()='" + paymentMethod + "']").click();
        BasicPage.popupWindow.shouldBe(Condition.exist);
        if($(".image-delete").exists()) {
            $(".image-delete").hover().click();
            Alert alert = webdriver().driver().switchTo().alert();
            sleep(1500);
            alert.accept();
        }
        button_SavePopUpWindow.click();
    }

    @And("Удаляем изображение способу доставки {string}")
    public void deleteImageToShippingMethod(String shippingMethod) {
        menu_Settings.click();
        section_ShippingMethod.click();
        $x("//div[@id='shippings_content']//a[text()='" + shippingMethod + "']").click();
        sleep(2000);
        if($(".image-delete").exists()) {
            $(".image-delete").hover().click();
            Alert alert = webdriver().driver().switchTo().alert();
            sleep(1500);
            alert.accept();
        }
        button_Save.click();
    }


    //Мобильное устройство
    SelenideElement mobile_MainMenu = $(".mobile-menu-toggler");
    SelenideElement mobile_ThemeActionsMenu = $(".actions-menu__dropdown-toggle");
    SelenideElement mobile_section_Themes = $("a[href*='dispatch=themes.manage'].main-menu-1__link");
    SelenideElement mobile_sectionLayouts = $(".actions-menu__dropdown-item-wrapper a[href$='block_manager.manage']");

    @Given("Переходим на страницу \"Веб-сайт -- Темы -- Макеты\" \\(mobile)")
    public void navigateTo_LayoutPage__mobile() {
        mobile_MainMenu.click();
        mobile_section_Themes.click();
        mobile_ThemeActionsMenu.click();
        mobile_sectionLayouts.click();
    }

    @And("Удаляем изображение способу оплаты {string} \\(mobile)")
    public void deleteImageToPaymentMethod__mobile(String paymentMethod) {
        mobile_MainMenu.click();
        menu_Settings.click();
        section_PaymentMethod.click();
        $x("//a[text()='" + paymentMethod + "']").scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
        BasicPage.popupWindow.shouldBe(Condition.exist);
        if($(".image-delete").exists()) {
            executeJavaScript("document.querySelector('input[id*='alt_icon_payment_image_']').scrollIntoView();");
            $("#alt_icon_payment_image_2").click();
            $(".image-delete").shouldBe(Condition.visible).click();
            Alert alert = webdriver().driver().switchTo().alert();
            sleep(1500);
            alert.accept();
        }
        button_SavePopUpWindow.click();
    }

    @And("Удаляем изображение способу доставки {string} \\(mobile)")
    public void deleteImageToShippingMethod__mobile(String shippingMethod) {
        mobile_MainMenu.click();
        menu_Settings.click();
        section_ShippingMethod.click();
        $x("//div[@id='shippings_content']//a[text()='" + shippingMethod + "']").scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
        sleep(2000);
        if($(".image-delete").exists()) {
            executeJavaScript("document.querySelector('input[id*='alt_icon_shipping_']').scrollIntoView();");
            $(".image-delete").click();
            Alert alert = webdriver().driver().switchTo().alert();
            sleep(1500);
            alert.accept();
        }
        button_Save.click();
    }
}