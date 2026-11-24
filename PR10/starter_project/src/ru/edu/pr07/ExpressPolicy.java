package ru.edu.pr07;

public class ExpressPolicy implements DeliveryPolicy {

    private static final double MAX_DISTANCE_KM = 50.0;
    private static final double MAX_WEIGHT_KG = 15.0;
    private static final double BASE_COST = 450.0;
    private static final double PER_KM_COST = 30.0;
    private static final double DISCOUNT_THRESHOLD = 10000.0;
    private static final double DISCOUNT_MULTIPLIER = 0.8;

    @Override
    public boolean supports(DeliveryRequest request) {
        if (request == null || request.type() != DeliveryType.EXPRESS) {
            return false;
        }
        return request.distanceKm() > 0 && request.distanceKm() <= MAX_DISTANCE_KM
                && request.weightKg() > 0 && request.weightKg() <= MAX_WEIGHT_KG;
    }

    @Override
    public double calculateCost(DeliveryRequest request) {
        if (!supports(request)) {
            throw new IllegalArgumentException("Express delivery is not supported for request: " + request);
        }
        double cost = BASE_COST + request.distanceKm() * PER_KM_COST;
        if (request.orderAmount() >= DISCOUNT_THRESHOLD) {
            cost *= DISCOUNT_MULTIPLIER;
        }
        return cost;
    }
}