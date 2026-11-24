package ru.edu.pr09;
import java.util.*; import java.util.stream.*;
public class SalesAnalyticsService {
 public List<Sale> topSales(List<Sale> sales,double min,int limit) { /* TODO 1 */ return List.of(); }
 public Optional<Sale> maxSale(List<Sale> sales) { /* TODO 2 */ return Optional.empty(); }
 public Map<String,Double> totalsByManager(List<Sale> sales) { /* TODO 3 */ return Map.of(); }
 public double averageByRegion(List<Sale> sales,String region) { /* TODO 4 */ return 0.0; }
}
