package steps;

import adminPanel.csCartPages.BasicPage;
import adminPanel.csCartPages.LayoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import storefront.CheckoutPage;
import storefront.HomePage;

import static com.codeborne.selenide.Selenide.*;

public class DropdownList_Ult_Test {
    BasicPage basicPage = new BasicPage();
    LayoutPage layoutPage = new LayoutPage();
    HomePage homePage = new HomePage();
    CheckoutPage checkoutPage = new CheckoutPage();
    SoftAssertions softAssertions = new SoftAssertions();

    @Given("Устанавливаем макет {string} по умолчанию")
    public void setLayout_asDefault(String layoutName){
        basicPage.navigateTo_LayoutPage();
        $x("//a[contains(text(), '(" + layoutName + ")')]").click();
        layoutPage.setLayoutAsDefault();
    }
    @And("Настраиваем блок {string} в виде выпадающего списка")
    public void setBlockAsDropDownList(String blockName) {
        layoutPage.setBlockAsDropDownList(blockName);
    }

    @When("Переходим на витрину")
    public void navigateToStorefrontMainPage() {
        basicPage.navigateToStorefrontMainPage();
    }

    @And("Авторизуемся на сайте \\(проверяем на уникальность ID)")
    public void authorizeOnStorefront() {
        checkoutPage.authorizeOnStorefront();
        basicPage.assertUniqueIDOnPage();
    }
    @And("Переходим на страницу категории {string} {string} \\(проверяем на уникальность ID)")
    public void navigateTo_CategoryPage(String mainCategory, String subCategory) {
        checkoutPage.navigateTo_CategoryPage(mainCategory, subCategory);
        basicPage.assertUniqueIDOnPage();
    }
    @And("Добавляем товар с опциями в корзину")
    public void addProductWithOptions() {
        checkoutPage.addProductWithOptions();
    }
    @And("Добавляем товар с вариациями в корзину")
    public void addProductWithVariations() {
        checkoutPage.addProductWithVariations();
    }
    @And("Переходим на страницу чекаута \\(проверяем на уникальность ID)")
    public void navigateTo_CheckoutPage() {
        homePage.header_Cart.click();
        homePage.button_Checkout.click();
        basicPage.assertUniqueIDOnPage();
    }
    @And("Используем промокод {string} \\(проверяем, что отобразился блок с применённой акцией)")
    public void usePromoCode(String promoCode) {
        checkoutPage.usePromoCode(promoCode);
    }
    @And("Выбираем способ доставки: {string}, {string}, {string} и выбираем пункт выдачи")
    public void selectShippingMethod(String country, String city, String shippingMethod) {
        checkoutPage.selectShippingMethod(country, city, shippingMethod);
    }
    @And("Выбираем способ оплаты {string}")
    public void selectPaymentMethod(String paymentMethod) {
        checkoutPage.selectPaymentMethod(paymentMethod);
    }
    @And("Ставим соглашения \\(проверяем на уникальность ID)")
    public void checkAgreements() {
        checkoutPage.checkAgreements();
        basicPage.assertUniqueIDOnPage();
    }
    @Then("Завершаем оформление заказа и проверяем, что мы на странице {string}")
    public void placeOrder(String pageBreadcrumb) {
        checkoutPage.button_PlaceOrder.click();
        softAssertions.assertThat($x("//bdi[text()='" + pageBreadcrumb + "']").exists())
                        .as("Заказ не оформлен успешно!");
        basicPage.assertUniqueIDOnPage();
    }
}