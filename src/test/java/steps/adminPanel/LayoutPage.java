package steps.adminPanel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LayoutPage {
    public LayoutPage(){super();}

    SelenideElement popupWindow = $(".ui-dialog-title");
    SelenideElement gearwheelOfActiveLayout = $(".with-menu.active .dropdown-toggle");
    SelenideElement button_makeByDefault = $(".with-menu.active a[href*='block_manager.set_default_layout']");
    SelenideElement layoutTab_Checkout = $x("//a[text()='Оформить заказ']");
    SelenideElement button_SettingsOfTemplate = $("a[id^='sw_case_settings_']");
    SelenideElement checkbox_DisplayAsDropDownList = $("input[id$='_properties_abt__ut2_as_select']");
    SelenideElement button_SaveBlockProperties = $("input[name='dispatch[block_manager.update_block]']");

    public void navigateToBlockSettings(String blockName) {
        layoutTab_Checkout.click();
        $("div[data-ca-block-name='" + blockName + "']").$(".bm-action-properties")
                .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
        popupWindow.shouldBe(Condition.visible);
        Selenide.executeJavaScript("arguments[0].click();", button_SettingsOfTemplate);
    }

    public void setLayout_asDefault(String layoutName){
        $x("//a[contains(text(), '(" + layoutName + ")')]").click();
        gearwheelOfActiveLayout.hover().click();
        if ($(".with-menu.active a[href*='block_manager.set_default_layout']").exists()) {
            button_makeByDefault.click();
            Selenide.sleep(2000);
        }
    }

    public void setBlockListType(String blockName, String listType) {
        navigateToBlockSettings(blockName);

        boolean shouldBeDropdown;
        if (listType.equalsIgnoreCase("выпадающего")) {
            shouldBeDropdown = true;
        } else if (listType.equalsIgnoreCase("обычного")) {
            shouldBeDropdown = false;
        } else {
            throw new IllegalArgumentException("Недопустимый тип списка: \"" + listType + "\". Разрешены только: \"выпадающего\" или \"обычного\".");
        }

        boolean isDropdownSelected = checkbox_DisplayAsDropDownList.isSelected();
        if (shouldBeDropdown != isDropdownSelected) {
            checkbox_DisplayAsDropDownList.click();
        }

        Selenide.executeJavaScript("arguments[0].click();", button_SaveBlockProperties);
    }
}