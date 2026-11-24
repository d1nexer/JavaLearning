package ru.edu.pr09;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = false;
        try {
            SalesAnalyticsService service = new SalesAnalyticsService();
            List<Sale> source = new ArrayList<>(List.of(
                    new Sale("S-01", "Ivanov", "North", 500.0),
                    new Sale("S-02", "Petrov", "North", 1200.0),
                    new Sale("S-03", "Ivanov", "South", 1200.0),
                    new Sale("S-04", "Sidorov", "West", 200.0)
            ));
            List<Sale> snapshotBefore = List.copyOf(source);

            List<Sale> top = service.topSales(source, 500.0, 2);
            Optional<Sale> max = service.maxSale(source);
            Map<String, Double> byManager = service.totalsByManager(source);
            double avgNorth = service.averageByRegion(source, "North");
            double avgUnknown = service.averageByRegion(source, "UnknownRegion");

            boolean invalidLimitThrown = false;
            try {
                service.topSales(source, 100.0, -1);
            } catch (IllegalArgumentException expected) {
                invalidLimitThrown = true;
            }

            ok = source.equals(snapshotBefore)
                    && top.size() == 2
                    && top.get(0).amount() == 1200.0
                    && max.isPresent()
                    && max.get().amount() == 1200.0
                    && byManager.get("Ivanov") == 1700.0
                    && avgNorth == 850.0
                    && avgUnknown == 0.0
                    && invalidLimitThrown;
        } catch (Exception e) {
            System.out.println("FAIL ошибка выполнения: " + e.getMessage());
        }
        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}