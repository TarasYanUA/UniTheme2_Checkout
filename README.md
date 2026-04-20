Данный проект разработан **для проверки страницы оформления заказа** в теме UniTheme2.
Актуальная версия UniTheme2 темы 4.20.1.b. Лучше устанавливать Пакет UniTheme2, но можно и тему отдельно.

Здесь используются следующие **библиотеки**:
* Selenide;
* JUnit5;
* Cucumber;
* Assertj - используется для написания удобных, выразительных и гибких проверок (ассёртов) в тестах на Java;
* Cucumber JSON Report;
* Файл логирования для SLF4J (библиотека logback-classic и его сопутствующий файл main -- logback.xml), чтобы отключить лишние логи.
* aspectjweaver (без него многие механизмы Cucumber не сработают) требует установки архива. Для этого требуется:
  - на компьютере должен присутствовать архив Maven (https://maven.apache.org/download.cgi) и путь к этому архиву должен быть указан в "переменных средах" компьютера.
  - далее уже в самом Intellij IDEA в его терминале ввести: mvn clean install -U. Это запустит установку нужного архива.
  - потом открыть консоль компьютера и ввести: mvn dependency:get -DgroupId=org.aspectj -DartifactId=aspectjweaver -Dversion=1.9.24

**Проект запускается** через файл **CucumberRunnerTest**, путь к файлу: src -- test -- java

**Cucumber JSON Report отчёт**:
- найти файл "target -- cucumber_target.html",
- на файле нажать правую кнопку мыши,
- выбрать "Open in -- Browser -- Chrome".