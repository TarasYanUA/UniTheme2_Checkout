@20
Feature: Чекаут с доставкой и оплатой в виде Выпадающего списка + Изображения + Неавторизованный пользователь

  Background: Работаем с панелью администратора
    Given Переходим на страницу "Дизайн -- Макеты"
    And Устанавливаем макет "Light" по умолчанию
    And Настраиваем блок "Способы доставки" в виде выпадающего списка
    And Настраиваем блок "Способы оплаты" в виде выпадающего списка
    And Добавляем изображение способу оплаты "Обсудить по телефону"
    And Добавляем изображение способу доставки "Самовывоз"

  Scenario: Чекаут с доставкой и оплатой в виде Выпадающего списка + Изображения + Неавторизованный пользователь
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
    And Выбираем способ доставки "Самовывоз" из выпадающего списка и выбираем пункт выдачи (скриншот "@20")
    And Выбираем способ оплаты "Обсудить по телефону" из выпадающего списка (скриншот "@20")
    And Ставим соглашения (проверяем на уникальность ID)
    Then Завершаем оформление заказа и проверяем, что мы на странице "Ваш заказ принят" (скриншот "@20")