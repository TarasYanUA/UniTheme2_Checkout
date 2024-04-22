package steps.adminPanel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import steps.storefront.HomePage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import org.openqa.selenium.Alert;

public class BasicPage {
    public BasicPage(){super();}

    //Общие методы, что применяются во многих классах
    public void focusBrowserTab(int tabNumber) {
        getWebDriver().getWindowHandle();
        switchTo().window(tabNumber);
    }

    public SelenideElement button_Save = $(".btn.btn-primary.cm-submit");
    public static SelenideElement popupWindow = $(".ui-dialog-title");
    public SelenideElement goTo_Storefront = $(".icon-shopping-cart");
    public SelenideElement gearWheelOnTop = $(".dropdown-icon--tools");
    private final SelenideElement menu_Design = $("#elm_menu_design");
    private final SelenideElement sectionLayouts = $("#elm_menu_design_layouts");
    private final SelenideElement menu_Administration = $("#elm_menu_administration");
    private final SelenideElement section_PaymentMethod = $("#elm_menu_administration_payment_methods");
    private final SelenideElement button_SaveMethod = $("input[value='Сохранить']");
    private final SelenideElement section_ShippingAndTaxes = $("#elm_menu_administration_shippings_taxes");
    private final SelenideElement section_ShippingMethod = $("#elm_menu_administration_shippings_taxes_shipping_methods");


    @Given("Переходим на страницу \"Дизайн -- Макеты\"")
    public void navigateTo_LayoutPage() {
        menu_Design.hover();
        sectionLayouts.click();
    }
    @When("Переходим на витрину")
    public HomePage navigateToStorefront_HomePage() {
        goTo_Storefront.scrollTo().click();
        focusBrowserTab(1);
        return new HomePage();
    }

    @And("Добавляем изображение способу оплаты {string}")
    public void addImageToPaymentMethod(String paymentMethod) {
        menu_Administration.hover();
        section_PaymentMethod.click();
        $x("//a[text()='" + paymentMethod + "']").click();
        BasicPage.popupWindow.shouldBe(Condition.exist);
        $x("//span[text()='" + paymentMethod + "']/../..//a[contains(@id, 'url_')]").click();
        Alert alert = webdriver().driver().switchTo().alert();
        sleep(1500);
        alert.sendKeys("https://dummyimage.com/50x50/09f/fff.png&text=a1");
        alert.accept();
        button_SaveMethod.click();
    }

    @And("Добавляем изображение способу доставки {string}")
    public void addImageToShippingMethod(String shippingMethod) {
        menu_Administration.hover();
        section_ShippingAndTaxes.hover();
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
}