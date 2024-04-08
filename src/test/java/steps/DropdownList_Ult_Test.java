package steps;

import adminPanel.csCartPages.BasicPage;
import adminPanel.csCartPages.LayoutPage;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import storefront.HomePage;

import static com.codeborne.selenide.Selenide.$x;

public class DropdownList_Ult_Test {
    BasicPage basicPage = new BasicPage();
    LayoutPage layoutPage = new LayoutPage();
    HomePage homePage = new HomePage();
    @Given("Устанавливаем макет Light v2 по умолчанию")
    public void setLayout_Light_asDefault(){
        LayoutPage layoutPage = basicPage.navigateTo_LayoutPage();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();
    }
    @And("Настраиваем блок {string} в виде выпадающего списка")
    public void setBlockAsDropDownList(String blockName) {
        layoutPage.layoutTab_Checkout.click();
       /* layoutPage.navigateToBlockSettings(blockName);
        layoutPage.button_SettingsOfTemplate.click();
        if(!layoutPage.checkbox_DisplayAsDropDownList.isSelected())
            layoutPage.checkbox_DisplayAsDropDownList.click();
        layoutPage.button_SaveBlockProperties.click();*/
    }

    @When("Переходим на витрину")
    public void navigateToStorefrontMainPage() {
        basicPage.storefrontMainPage.scrollTo().click();
        basicPage.focusBrowserTab(1);
        homePage.cookie.click();
        basicPage.selectLanguage("ru");
    }

    @And("Авторизуемся на сайте")
    public void authorizeOnStorefront() {
        homePage.myAccount.click();
        if(!homePage.button_LogOut.exists()){
            homePage.button_SignIn.click();
            BasicPage.popupWindow.shouldBe(Condition.visible);
            homePage.button_SignIn_Popup.click();
        }
    }
    @And("Переходим на страницу категории {string} {string}")
    public void navigateTo_CategoryPage(String mainCategory, String subCategory) {
        $x("//li[contains(@class, 'ty-menu-item__" + mainCategory + "')]//a[contains(@href, '" + subCategory + "/')]").click();
    }
}