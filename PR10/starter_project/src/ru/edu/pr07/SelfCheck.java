package ru.edu.pr07;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = false;
        try {
            DeliveryPolicyFactory factory = new DeliveryPolicyFactory();
            DeliveryService service = new DeliveryService(factory);

            double pickupCost = service.calculate(new DeliveryRequest(DeliveryType.PICKUP, 0, 10, 1000));
            double standardCost = service.calculate(new DeliveryRequest(DeliveryType.STANDARD, 10, 5, 2000));
            double expressCost = service.calculate(new DeliveryRequest(DeliveryType.EXPRESS, 10, 3, 2000));

            boolean unsupportedThrown = false;
            try {
                service.calculate(new DeliveryRequest(DeliveryType.EXPRESS, 200, 3, 2000));
            } catch (IllegalArgumentException expected) {
                unsupportedThrown = true;
            }

            ok = (pickupCost == 0.0)
                    && (standardCost == 200.0 + 10 * 15.0 + 5 * 10.0)
                    && (expressCost == 450.0 + 10 * 30.0)
                    && unsupportedThrown;
        } catch (Exception e) {
            System.out.println("FAIL exception " + e.getMessage());
        }
        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}