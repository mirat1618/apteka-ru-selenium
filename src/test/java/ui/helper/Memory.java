package ui.helper;

public class Memory {
    public static Double currentCardTotalPrice;

    public static Double getCurrentCardTotalPrice() {
        return currentCardTotalPrice;
    }

    public static void setCurrentCardTotalPrice(Double currentCardTotalPrice) {
        Memory.currentCardTotalPrice = currentCardTotalPrice;
    }
}
