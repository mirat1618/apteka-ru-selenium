@Sum
Feature: Подсчет итоговой суммы
  Scenario: Проверка суммы итоговой суммы в корзине
    Given Пользователь находится на главной странице
    When Пользователь вводит "Гематоген" в поле поиска и нажимает Enter
    And Пользователь добавляет первые 5 элементов в корзину и суммирует цены добавленных товаров
    And Пользователь переходит в корзину
    Then Значение поля Итого в корзине равно суммированной цене

