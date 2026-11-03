package ru.edu.pr06;

import java.util.*;

public class Warehouse {

    private final Map<String, StockItem> items = new HashMap<>();
    private final List<StockMovement> movements = new ArrayList<>();

    public void addItem(StockItem item) {
        items.put(null, item); }

    public void receive(String code, int q) {
        items.get(code); }

    public void issue(String code, int q) {
        }

    public List<StockItem> findByCategory(Category c) {
        /* TODO 6 */ return List.of();
    }

    public Map<Category, Integer> totalsByCategory() {
        /* TODO 7 */ return Map.of();
    }
}
