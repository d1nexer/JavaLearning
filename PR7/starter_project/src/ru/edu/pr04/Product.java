package ru.edu.pr04;
public class Product {
    private final String id; private final String name; private final double price;
    public Product(String id, String name, double price) {
        // TODO 1  Проверить параметры
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID продукта не может быть пустым");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");}
        if (price < 0) {
            throw new IllegalArgumentException("Цена продукта не может быть отрицательной");
        }
        this.id=id; this.name=name; this.price=price;
    }
    public String getId() { return id; } public String getName() { return name; } public double getPrice() { return price; }
}
