package ru.edu.pr01;

public class OrderCalculator {

    public static boolean isValid(int quantity, double unitPrice, double discountPercent) {
        if (quantity <= 0 || unitPrice < 0 || discountPercent < 0) {
            return false;
        }
        return quantity <= 10000
                && unitPrice <= 5000000
                && discountPercent <= 30;
    }

    public static double calculateBase(int quantity, double unitPrice) {
        return quantity * unitPrice;
    }

    public static double applyDiscount(double base, double discountPercent) {
        return base - (base * discountPercent / 100);
    }

    public static double calculateVat(double discounted, double vatPercent) {
        return discounted * vatPercent / 100.0;
    }

    public static double calculateTotal(int quantity, double unitPrice, double discountPercent, double vatPercent) {
        if (!isValid(quantity, unitPrice, discountPercent)) {
            return 0.0;
        }
        double discounted_base = applyDiscount(calculateBase(quantity, unitPrice), discountPercent);
        double vat = calculateVat(discounted_base, vatPercent);
        return Math.round((discounted_base + vat) * 100.0) / 100.0;
    }
}

