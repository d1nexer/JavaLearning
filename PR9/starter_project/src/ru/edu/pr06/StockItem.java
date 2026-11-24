package ru.edu.pr06;

import java.util.Objects;

public class StockItem {

    private final String code;
    private final String name;
    private final Category category;
    private int quantity;

    public StockItem(String code, String name, Category category, int quantity) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Инвентарный код не может быть пустым");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Наименование не может быть пустым");
        }
        if (category == null) {
            throw new IllegalArgumentException("Категория не может быть null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Начальный остаток не может быть отрицательным");
        }
        this.code = code;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public void increase(int q) {
        if (q <= 0) {
            throw new IllegalArgumentException("Количество прихода должно быть больше нуля");
        }
        quantity += q;
    }

    public void decrease(int q) {
        if (q <= 0) {
            throw new IllegalArgumentException("Количество списания должно быть больше нуля");
        }
        if (quantity < q) {
            throw new IllegalArgumentException("Недостаточно остатка для списания");
        }
        quantity -= q;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Objects.equals(code, stockItem.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}