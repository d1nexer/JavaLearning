package ru.edu.pr06;

public class StockItem {

    private final String code;
    private final String name;
    private final Category category;
    private int quantity;

    public StockItem(String code, String name, Category category, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException();
        
        }this.code = code;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public void increase(int q) {
        quantity+= q;}

    public void decrease(int q) {
        quantity-=q;}

    public String getCode() {
        return code;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }
}
