package ru.edu.pr04;
import java.util.*;
public class Order {
    private final Employee employee; private final List<OrderItem> items=new ArrayList<>(); private OrderStatus status=OrderStatus.DRAFT;
    public Order(Employee employee) { this.employee=Objects.requireNonNull(employee); }
    public void addProduct(Product product,int quantity) {
        if (status == OrderStatus.CONFIRMED) throw new IllegalStateException("Подтвержденный заказ не может быть изменен");
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increase(quantity);
                return;
            }
        }
        items.add(new OrderItem(product, quantity));
    }
    public void removeProduct(String productId) {
        if (status == OrderStatus.CONFIRMED) throw new IllegalStateException("Подтвержденный заказ не может быть изменен");
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    public double total() {
        double total = 0.0;
        for (OrderItem item : items) total += item.getLineTotal();
        return total;
    }
    public void confirm() {
        if (items.isEmpty()) throw new IllegalStateException("Необходимо добавить хотя бы один товар в заказ перед подтверждением");
        this.status = OrderStatus.CONFIRMED;
    }
    public List<OrderItem> getItems() { return List.copyOf(items); }
    public OrderStatus getStatus() { return status; }
}
