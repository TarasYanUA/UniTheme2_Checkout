@140
Feature: Чекаут со способом доставки и способом оплаты в виде Обычного списка + Неавторизованный пользователь

  Background: Работаем с панелью администратора
    Given Переходим на страницу "Дизайн -- Макеты"
    And Устанавливаем макет "Advanced" по умолчанию
    And Настраиваем блок "Способы доставки" в виде обычного списка
    And Настраиваем блок "Способы оплаты" в виде обычного списка

  Scenario: Чекаут со способом доставки и способом оплаты в виде Обычного списка + Неавторизованный пользователь
    When Переходим на витрину
    And Переключаемся на "ru" язык интерфейса витрины
    And Выходим из личного кабинета (проверяем на уникальность ID)
    And Переходим на страницу категории "electronics" "planshety" (проверяем на уникальность ID)
    And Добавляем товар с опциями в корзину
    And Переходим на страницу категории "apparel" "muzhskaya-odezhda" (проверяем на уникальность ID)
    And Добавляем товар с вариациями в корзину
    And Переходим на страницу чекаута (проверяем на уникальность ID)
    And Используем промокод "sale-20" (проверяем, что отобразился блок с применённой акцией)
    And Заполняем обязательные поля для неавторизованных пользователей
    And Выбираем страну и город доставки: "Россия", "Москва (Москва)"
    And Выбираем способ доставки "Обсудить с менеджером" для первого продавца из обычного списка
    And Выбираем способ доставки "Самовывоз" из обычного списка и выбираем пункт выдачи (скриншот "@140")
    And Выбираем способ оплаты "Обсудить по телефону" из обычного списка (скриншот "@140")
    And Ставим соглашения (проверяем на уникальность ID)
    Then Завершаем оформление заказа и проверяем, что мы на странице "Ваш заказ принят" (скриншот "@140")