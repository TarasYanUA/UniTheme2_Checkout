package steps.adminPanel.csCartPages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import steps.storefront.HomePage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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
    private final SelenideElement menuDesign = $("#elm_menu_design");
    private final SelenideElement sectionLayouts = $("#elm_menu_design_layouts");

    @Given("Переходим на страницу \"Дизайн -- Макеты\"")
    public void navigateTo_LayoutPage() {
        menuDesign.hover();
        sectionLayouts.click();
    }
    @When("Переходим на витрину")
    public HomePage navigateToStorefront_HomePage() {
        goTo_Storefront.scrollTo().click();
        focusBrowserTab(1);
        return new HomePage();
    }
}