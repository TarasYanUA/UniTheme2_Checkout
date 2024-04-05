package adminPanel.csCartPages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

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
}
