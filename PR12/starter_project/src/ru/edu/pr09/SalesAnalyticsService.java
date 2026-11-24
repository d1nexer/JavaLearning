package ru.edu.pr09;

import java.util.*;
import java.util.stream.*;

public class SalesAnalyticsService {

    private static final int SECONDARY_SORT_LIMIT = 15;

    public List<Sale> topSales(List<Sale> sales, double min, int limit) {
        if (sales == null) {
            throw new IllegalArgumentException("Список продаж не должен быть null");
        }
        if (Double.isNaN(min) || Double.isInfinite(min)) {
            throw new IllegalArgumentException("Минимальная сумма продажи должна быть конечным числом");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("Ограничение количества элементов (limit) не может быть отрицательным");
        }
        if (limit == 0 || sales.isEmpty()) {
            return List.of();
        }

        return sales.stream()
                .filter(Objects::nonNull)
                .filter(sale -> sale.amount() >= min)
                .sorted(saleComparator())
                .limit(limit)
                .toList();
    }

    public Optional<Sale> maxSale(List<Sale> sales) {
        if (sales == null) {
            throw new IllegalArgumentException("Список продаж не должен быть null");
        }
        return sales.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(Sale::amount));
    }

    public Map<String, Double> totalsByManager(List<Sale> sales) {
        if (sales == null) {
            throw new IllegalArgumentException("Список продаж не должен быть null");
        }
        Map<String, Double> totals = sales.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Sale::manager,
                        Collectors.summingDouble(Sale::amount)
                ));
        return new LinkedHashMap<>(totals);
    }

    public double averageByRegion(List<Sale> sales, String region) {
        if (sales == null) {
            throw new IllegalArgumentException("Список продаж не должен быть null");
        }
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Регион не должен быть пустым или null");
        }
        return sales.stream()
                .filter(Objects::nonNull)
                .filter(sale -> region.equals(sale.region()))
                .mapToDouble(Sale::amount)
                .average()
                .orElse(0.0);
    }

    private Comparator<Sale> saleComparator() {
        return Comparator.comparingDouble(Sale::amount)
                .reversed()
                .thenComparing(sale -> boundedSecondaryKey(sale.id()));
    }

    private String boundedSecondaryKey(String value) {
        if (value == null) {
            return "";
        }
        return value.length() <= SECONDARY_SORT_LIMIT
                ? value
                : value.substring(0, SECONDARY_SORT_LIMIT);
    }
}