Данный проект разработан для проверки страницы оформления заказа в теме UniTheme2.
Актуальная версия UniTheme2 темы 4.17.2d. Можно установить как саму тему отдельно, так и Пакет UniTheme2 (UltRu).

Здесь используются следующие библиотеки:
* Selenide;
* Cucumber;
* Assertj Core;
* Allure surefire-report.

Проект можно запустить следующими вариантами:
1) через Allure surefire отчёт: перейти в "Terminal" и ввести "mvn clean test". По окончанию теста в папке "target -> surefire reports" открыть файл "index.html" с помощью браузера.
2) через файл CucumberRunnerTest
3) через файл DropdownList_Ult.feature, путь к файлу: test -- java -- resources -- features