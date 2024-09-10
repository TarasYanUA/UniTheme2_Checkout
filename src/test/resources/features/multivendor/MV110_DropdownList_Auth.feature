@MV110
Feature:(MV) Чекаут со способом доставки и способом оплаты в виде выпадающего списка + Авторизованный пользователь

  Background: Работаем с панелью администратора
    Given Переходим на страницу "Веб-сайт -- Темы -- Макеты"
    And Устанавливаем макет "Advanced" по умолчанию
    And Настраиваем блок "Способы доставки" в виде выпадающего списка
    And Настраиваем блок "Способы оплаты" в виде выпадающего списка

  Scenario:(MV) Чекаут со способом доставки и способом оплаты в виде выпадающего списка + Авторизованный пользователь
    When Переходим на витрину
    And Переключаемся на "ru" язык интерфейса витрины
    And Авторизуемся на сайте (проверяем на уникальность ID)
    And Переходим на страницу категории "electronics" "planshety" (проверяем на уникальность ID)
    And Добавляем товар с опциями в корзину
    And Переходим на страницу категории "apparel" "muzhskaya-odezhda" (проверяем на уникальность ID)
    And Добавляем товар с вариациями в корзину
    And Переходим на страницу чекаута (проверяем на уникальность ID)
    And Используем промокод "sale-20" (проверяем, что отобразился блок с применённой акцией)
    And Выбираем страну и город доставки: "Россия", "Москва (Москва)"
    And Выбираем способ доставки "Обсудить с менеджером" для первого продавца из выпадающего списка
    And Выбираем способ доставки "Самовывоз" из выпадающего списка и выбираем пункт выдачи (скриншот "@110")
    And Выбираем способ оплаты "Обсудить по телефону" из выпадающего списка (скриншот "@110")
    And Ставим соглашения (проверяем на уникальность ID)
    Then Завершаем оформление заказа и проверяем, что мы на странице "Ваш заказ принят" (скриншот "@110")