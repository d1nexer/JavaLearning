package ru.edu.pr06;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = false;
        try {
            Warehouse warehouse = new Warehouse();
            StockItem item = new StockItem("PC-001", "Workstation", Category.COMPUTER, 10);
            warehouse.addItem(item);

            warehouse.receive("PC-001", 5);
            warehouse.issue("PC-001", 3);

            if (warehouse.getItem("PC-001").getQuantity() == 12) {
                var totals = warehouse.totalsByCategory();
                if (totals.get(Category.COMPUTER) == 12) {
                    ok = true;
                }
            }
        } catch (Exception e) {
            System.out.println("FAIL exception " + e.getMessage());
        }
        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}