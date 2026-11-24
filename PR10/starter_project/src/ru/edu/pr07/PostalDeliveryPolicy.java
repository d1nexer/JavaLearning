package ru.edu.pr07;

public class PostalDeliveryPolicy implements DeliveryPolicy {

    private static final double MAX_DISTANCE_KM = 2000.0;
    private static final double MAX_WEIGHT_KG = 20.0;
    private static final double BASE_COST = 250.0;
    private static final double PER_KG_COST = 25.0;

    @Override
    public boolean supports(DeliveryRequest request) {
        if (request == null || request.type() != DeliveryType.POSTAL) {
            return false;
        }
        return request.distanceKm() > 0 && request.distanceKm() <= MAX_DISTANCE_KM
                && request.weightKg() > 0 && request.weightKg() <= MAX_WEIGHT_KG;
    }

    @Override
    public double calculateCost(DeliveryRequest request) {
        if (!supports(request)) {
            throw new IllegalArgumentException("Postal delivery is not supported for request: " + request);
        }
        return BASE_COST + request.weightKg() * PER_KG_COST;
    }
}