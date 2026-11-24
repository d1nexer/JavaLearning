package ru.edu.pr06;

import java.util.*;

public class Warehouse {

    private final Map<String, StockItem> items = new HashMap<>();
    private final List<StockMovement> movements = new ArrayList<>();

    public void addItem(StockItem item) {
        if (item == null || item.getCode() == null) {
            throw new IllegalArgumentException("Товар и инвентарный код не могут быть null");
        }
        if (items.containsKey(item.getCode())) {
            throw new IllegalArgumentException("Товар с кодом " + item.getCode() + " уже существует");
        }
        items.put(item.getCode(), item);
    }

    public void receive(String code, int q) {
        if (q <= 0) {
            throw new IllegalArgumentException("Количество прихода должно быть положительным");
        }
        StockItem item = items.get(code);
        if (item == null) {
            throw new IllegalArgumentException("Позиция с кодом " + code + " не найдена");
        }
        item.increase(q);
        movements.add(new StockMovement(code, MovementType.RECEIVE, q));
    }

    public void issue(String code, int q) {
        if (q <= 0) {
            throw new IllegalArgumentException("Количество выдачи должно быть положительным");
        }
        StockItem item = items.get(code);
        if (item == null) {
            throw new IllegalArgumentException("Позиция с кодом " + code + " не найдена");
        }
        if (item.getQuantity() < q) {
            throw new IllegalArgumentException("Недостаточно товара на складе. Доступно: " + item.getQuantity() + ", запрошено: " + q);
        }
        item.decrease(q);
        movements.add(new StockMovement(code, MovementType.ISSUE, q));
    }

    public List<StockItem> findByCategory(Category c) {
        if (c == null) {
            return List.of();
        }
        List<StockItem> result = new ArrayList<>();
        for (StockItem item : items.values()) {
            if (item.getCategory() == c) {
                result.add(item);
            }
        }
        return result;
    }

    public Map<Category, Integer> totalsByCategory() {
        Map<Category, Integer> totals = new EnumMap<>(Category.class);
        for (Category cat : Category.values()) {
            totals.put(cat, 0);
        }
        for (StockItem item : items.values()) {
            totals.put(item.getCategory(), totals.get(item.getCategory()) + item.getQuantity());
        }
        return totals;
    }

    public StockItem getItem(String code) {
        return items.get(code);
    }

    public List<StockMovement> getMovements() {
        return Collections.unmodifiableList(movements);
    }

    public Set<Category> getActiveCategories() {
        Set<Category> categories = EnumSet.noneOf(Category.class);
        for (StockItem item : items.values()) {
            categories.add(item.getCategory());
        }
        return Collections.unmodifiableSet(categories);
    }

    public Set<String> getAllItemCodes() {
        return Collections.unmodifiableSet(new HashSet<>(items.keySet()));
    }
}