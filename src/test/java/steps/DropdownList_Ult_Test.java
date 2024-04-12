package steps;

import steps.adminPanel.csCartPages.BasicPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import steps.storefront.CheckoutPage;
import steps.storefront.HomePage;

import static com.codeborne.selenide.Selenide.*;

public class DropdownList_Ult_Test {
    BasicPage basicPage = new BasicPage();
    HomePage homePage = new HomePage();
    CheckoutPage checkoutPage = new CheckoutPage();
    SoftAssertions softAssertions = new SoftAssertions();

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
        sleep(2000);
        basicPage.assertUniqueIDOnPage();
    }
}