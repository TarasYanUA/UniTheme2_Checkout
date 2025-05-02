package steps.storefront;

import hooks.AssertUniqueIDOnPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class HomePageSteps implements AssertUniqueIDOnPage {
    public HomePageSteps() { super(); }

    HomePage homePage = new HomePage();
    HomePageMobile homePageMobile = new HomePageMobile();

    @And("Переключаемся на {string} язык интерфейса витрины")
    public void selectLanguage(String langCode) {
        homePage.selectLanguage(langCode);
    }

    @And("Авторизуемся на сайте \\(проверяем на уникальность ID)")
    public void authorizeOnStorefront() {
        homePage.authorize();
        assertUniqueIDOnPage();
    }

    @And("Выходим из личного кабинета \\(проверяем на уникальность ID)")
    public void unAuthorizeOnStorefront() {
        homePage.logout();
        assertUniqueIDOnPage();
    }

    @And("Переходим на страницу категории {string} {string} \\(проверяем на уникальность ID)")
    public void navigateTo_CategoryPage(String mainCategory, String subCategory) {
        homePage.navigateToCategory(mainCategory, subCategory);
        assertUniqueIDOnPage();
    }

    @And("Добавляем товар с опциями в корзину")
    public void addProductWithOptions() {
        homePage.addProductWithOptions();
    }

    @And("Добавляем товар с вариациями в корзину")
    public void addProductWithVariations() {
        homePage.addProductWithVariations();
    }

    @And("Переходим на страницу чекаута \\(проверяем на уникальность ID)")
    public void navigateTo_CheckoutPage() {
        homePage.goToCheckout();
        assertUniqueIDOnPage();
    }


    // Мобильное устройство
    @Given("Разавторизоваться на витрине")
    public void logoutOnStorefront() {
        homePageMobile.logoutMobile();
    }

    @And("Переходим на страницу категории {string} {string} \\(mobile)")
    public void navigateTo_CategoryPage__mobile(String mainCategory, String subCategory) {
        homePageMobile.navigateToCategoryMobile(mainCategory, subCategory);
    }

    @And("Добавляем товар с опциями в корзину и переходим на страницу чекаута")
    public void addProductWithOptions__mobile() {
        homePageMobile.addProductWithOptionsAndGoToCheckoutMobile();
    }
}