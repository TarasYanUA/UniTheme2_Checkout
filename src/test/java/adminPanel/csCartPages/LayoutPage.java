package adminPanel.csCartPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LayoutPage {
    public LayoutPage(){super();}

    public SelenideElement layout_LightV2 = $x("//a[contains(text(), '(Light v2)')]");
    private SelenideElement gearwheelOfActiveLayout = $(".with-menu.active .dropdown-toggle");
    private SelenideElement button_makeByDefault = $(".with-menu.active a[href*='block_manager.set_default_layout']");
    public void setLayoutAsDefault() {
        gearwheelOfActiveLayout.hover().click();
        if ($(".with-menu.active a[href*='block_manager.set_default_layout']").exists()) {
            button_makeByDefault.click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public SelenideElement layoutTab_Checkout = $x("//a[text()='Оформить заказ']");
    public void navigateToBlockSettings(String blockName) {
        $("div[data-ca-block-name='" + blockName + "']").$(".icon-cog").click();
        BasicPage.popupWindow.shouldBe(Condition.visible);
    }

}
