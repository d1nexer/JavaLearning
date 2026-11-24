package ru.edu.pr07;

public class StandardCourierPolicy implements DeliveryPolicy {

    private static final double MAX_DISTANCE_KM = 100.0;
    private static final double MAX_WEIGHT_KG = 30.0;
    private static final double BASE_COST = 200.0;
    private static final double PER_KM_COST = 15.0;
    private static final double PER_KG_COST = 10.0;
    private static final double MIN_COST = 15.0;

    @Override
    public boolean supports(DeliveryRequest request) {
        if (request == null || request.type() != DeliveryType.STANDARD) {
            return false;
        }
        return request.distanceKm() >= 0 && request.distanceKm() <= MAX_DISTANCE_KM
                && request.weightKg() > 0 && request.weightKg() <= MAX_WEIGHT_KG;
    }

    @Override
    public double calculateCost(DeliveryRequest request) {
        if (!supports(request)) {
            throw new IllegalArgumentException("Standard delivery is not supported for request: " + request);
        }
        double rawCost = BASE_COST
                + request.distanceKm() * PER_KM_COST
                + request.weightKg() * PER_KG_COST;
        return Math.max(MIN_COST, rawCost);
    }
}