package adminPanel.csCartPages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BasicPage {
    public BasicPage(){super();}

    public SelenideElement button_Save = $(".btn.btn-primary.cm-submit");
    public static SelenideElement popupWindow = $(".ui-dialog-title");
    public SelenideElement button_Storefront = $(".icon-shopping-cart");
    public SelenideElement gearWheelOnTop = $(".dropdown-icon--tools");
    private SelenideElement menuDesign = $("#elm_menu_design");
    private SelenideElement sectionLayouts = $("#elm_menu_design_layouts");
    public LayoutPage navigateTo_LayoutPage(){
        menuDesign.hover();
        sectionLayouts.click();
        return new LayoutPage();
    }
    public SelenideElement storefrontMainPage = $(".icon-shopping-cart");
    public void focusBrowserTab(int tabNumber) {
        getWebDriver().getWindowHandle();
        switchTo().window(tabNumber);
    }
    public void selectLanguage(String lang_RuEnAr) {
        $("a[id*='_wrap_language_']").hover().click();
        $(".ty-select-block__list-item a[data-ca-name='" + lang_RuEnAr + "']").click();
    }
}
