@Mob210
Feature:(Mobile) Чекаут со способом доставки и способом оплаты в виде выпадающего списка + Авторизованный пользователь

  Background: Работаем с панелью администратора
#    Given Переходим на страницу "Веб-сайт -- Темы -- Макеты" (mobile)
#    And Настраиваем блок "Способы доставки" в виде выпадающего списка (mobile)
#    And Настраиваем блок "Способы оплаты" в виде выпадающего списка (mobile)
#    And Удаляем изображение способу оплаты "Обсудить по телефону" (mobile)
#    And Удаляем изображение способу доставки "Самовывоз" (mobile)

  Scenario:(Mobile) Чекаут со способом доставки и способом оплаты в виде выпадающего списка + Авторизованный пользователь
    When Переходим на витрину
    Given Разавторизоваться на витрине
    And Переключаемся на "ru" язык интерфейса витрины
    And Переходим на страницу категории "electronics" "Компьютеры" (mobile)
    And Добавляем товар с опциями в корзину и переходим на страницу чекаута
    And Используем промокод "sale-20" (проверяем, что отобразился блок с применённой акцией)
    And Выбираем страну и город доставки: "Россия", "Москва (Москва)"
    And Выбираем способ доставки "Самовывоз" из выпадающего списка и выбираем пункт выдачи (скриншот "@210")
    And Выбираем способ оплаты "Обсудить по телефону" из выпадающего списка (скриншот "@210")
    And Ставим соглашения (проверяем на уникальность ID)
    Then Завершаем оформление заказа и проверяем, что мы на странице "Ваш заказ принят" (скриншот "@210")