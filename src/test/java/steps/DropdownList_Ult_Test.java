package steps;

import adminPanel.csCartPages.BasicPage;
import adminPanel.csCartPages.LayoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class DropdownList_Ult_Test {
    BasicPage basicPage = new BasicPage();
    LayoutPage layoutPage = new LayoutPage();
    @Given("Устанавливаем макет Light v2 по умолчанию")
    public void setLayout_LightV2_asDefault (){
        LayoutPage layoutPage = basicPage.navigateTo_LayoutPage();
        layoutPage.layout_LightV2.click();
        layoutPage.setLayoutAsDefault();
    }
    @And("Настраиваем блок {string} в виде выпадающего списка")
    public void setBlockAsDropDownList(String blockName) {
        layoutPage.layoutTab_Checkout.click();
        layoutPage.navigateToBlockSettings(blockName);
        layoutPage.button_SettingsOfTemplate.click();
        if(!layoutPage.checkbox_DisplayAsDropDownList.isSelected())
            layoutPage.checkbox_DisplayAsDropDownList.click();
        layoutPage.button_SaveBlockProperties.click();
    }
}