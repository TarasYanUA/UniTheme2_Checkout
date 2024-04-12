package adminPanel.csCartPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LayoutPage {
    public LayoutPage(){super();}

    private SelenideElement gearwheelOfActiveLayout = $(".with-menu.active .dropdown-toggle");
    private SelenideElement button_makeByDefault = $(".with-menu.active a[href*='block_manager.set_default_layout']");
    public void setLayoutAsDefault() {
        gearwheelOfActiveLayout.hover().click();
        if ($(".with-menu.active a[href*='block_manager.set_default_layout']").exists()) {
            button_makeByDefault.click();
            Selenide.sleep(1500);
        }
    }
    public SelenideElement layoutTab_Checkout = $x("//a[text()='Оформить заказ']");
    public void navigateToBlockSettings(String blockName) {
        $("div[data-ca-block-name='" + blockName + "']").$(".icon-cog").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
    }
    public SelenideElement button_SettingsOfTemplate = $("a[id^='sw_case_settings_']");
    public SelenideElement checkbox_DisplayAsDropDownList = $("input[id$='_properties_abt__ut2_as_select']");
    public SelenideElement button_SaveBlockProperties = $("input[name=\"dispatch[block_manager.update_block]\"]");

    public void setBlockAsDropDownList(String blockName) {
        layoutTab_Checkout.click();
        navigateToBlockSettings(blockName);
        button_SettingsOfTemplate.click();
        if(!checkbox_DisplayAsDropDownList.isSelected())
            checkbox_DisplayAsDropDownList.click();
        button_SaveBlockProperties.click();
    }
}