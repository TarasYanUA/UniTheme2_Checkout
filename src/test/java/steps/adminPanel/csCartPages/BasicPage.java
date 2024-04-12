package steps.adminPanel.csCartPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import steps.storefront.HomePage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BasicPage {
    public BasicPage(){super();}
    HomePage homePage = new HomePage();

    //Общие методы, что применяются во многих классах
    public void focusBrowserTab(int tabNumber) {
        getWebDriver().getWindowHandle();
        switchTo().window(tabNumber);
    }
    public static void assertUniqueIDOnPage() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        //Собираем первую коллекцию по селектору
        ElementsCollection collectionOne_currentID = $$x("//*[@id]");
        // Направляем значения из первой коллекции во вторую
        List<String> collectionTwo_listOfID = new ArrayList<>();
        for (SelenideElement element : collectionOne_currentID) {
            String idValue = element.getAttribute("id");
            collectionTwo_listOfID.add(idValue);
        }
        // Поиск повторяющихся ID
        Set<String> collectionThree_duplicatedID = new HashSet<>();
        for (String idValue : collectionTwo_listOfID) {
            // Если ID уже присутствует в коллекции collectionTwo_listOfID, то это повторяющийся ID и он записывается в collectionThree_duplicatedID
            if (collectionTwo_listOfID.indexOf(idValue) != collectionTwo_listOfID.lastIndexOf(idValue)) {
                collectionThree_duplicatedID.add(idValue);
            }
        }
        // Вывод на экран неуникальных ID
        if(!collectionThree_duplicatedID.isEmpty()) {
            System.out.println("\n\nКоллекция ID НЕ уникальна на странице: " + currentUrl + " Список не уникальных ID: ");
            for (String id : collectionThree_duplicatedID) {
                System.out.print(id + ", ");
            }
        }
    }   //collectionThree_duplicatedID.forEach(id -> System.out.println(id + ", ")); //лямбда-выражение

    public SelenideElement button_Save = $(".btn.btn-primary.cm-submit");
    public static SelenideElement popupWindow = $(".ui-dialog-title");
    public SelenideElement goTo_Storefront = $(".icon-shopping-cart");
    public SelenideElement gearWheelOnTop = $(".dropdown-icon--tools");
    private final SelenideElement menuDesign = $("#elm_menu_design");
    private final SelenideElement sectionLayouts = $("#elm_menu_design_layouts");

    @Given("Переходим на страницу \"Дизайн -- Макеты\"")
    public LayoutPage navigateTo_LayoutPage() {
        menuDesign.hover();
        sectionLayouts.click();
        return new LayoutPage();
    }
    @When("Переходим на витрину")
    public HomePage navigateToStorefrontMainPage() {
        goTo_Storefront.scrollTo().click();
        focusBrowserTab(1);
        return new HomePage();
    }
}