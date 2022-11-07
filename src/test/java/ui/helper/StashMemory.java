package ui.helper;

/* Вспомогательный класс для хранения данных во время тестов */
public class StashMemory {
    public static Double currentCardTotalPrice; // Переменная для хранения подсчитанной заранее стоимости товаров в корзине

    /* Getters and setters */
    public static Double getCurrentCardTotalPrice() {
        return currentCardTotalPrice;
    }

    public static void setCurrentCardTotalPrice(Double currentCardTotalPrice) {
        StashMemory.currentCardTotalPrice = currentCardTotalPrice;
    }
}
