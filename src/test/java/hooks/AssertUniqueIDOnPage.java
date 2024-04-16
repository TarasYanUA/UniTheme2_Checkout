package hooks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$$x;

public interface AssertUniqueIDOnPage {
    default void assertUniqueIDOnPage() {
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
            System.out.println("Коллекция ID НЕ уникальна на странице: " + currentUrl);
            System.out.println("Список не уникальных ID: ");
            for (String id : collectionThree_duplicatedID) {
                System.out.print(id + ", ");
            }    //collectionThree_duplicatedID.forEach(id -> System.out.println(id + ", ")); //лямбда-выражение
            System.out.println();
        }
    }
}
