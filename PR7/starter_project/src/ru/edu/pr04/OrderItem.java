package ru.edu.pr04;
public class OrderItem {
    private final Product product; private int quantity;
    public OrderItem(Product product,int quantity) { this.product=product; setQuantity(quantity); }
    private void setQuantity(int quantity) {
        if (quantity < 1 || quantity > 110) {
            throw new IllegalArgumentException("Количество должно быть между 1 и 110");//модификатор 10 
        }
        this.quantity=quantity;
    }
    public void increase(int delta) {
        if (delta <= 0 || delta > 110 - quantity) {
            throw new IllegalArgumentException("Количество должно быть между 1 и 110"); //можификатор 10 
        }
        setQuantity(quantity + delta);
    }
    public Product getProduct() { return product; } public int getQuantity() { return quantity; }
    public double getLineTotal() { return product.getPrice() * quantity; }
}
