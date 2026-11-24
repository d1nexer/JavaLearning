package ru.edu.pr07;

import java.util.EnumMap;
import java.util.Map;

public class DeliveryPolicyFactory {

    private final Map<DeliveryType, DeliveryPolicy> policies = new EnumMap<>(DeliveryType.class);

    public DeliveryPolicyFactory() {
        policies.put(DeliveryType.PICKUP, new PickupPolicy());
        policies.put(DeliveryType.STANDARD, new StandardCourierPolicy());
        policies.put(DeliveryType.EXPRESS, new ExpressPolicy());
        policies.put(DeliveryType.POSTAL, new PostalDeliveryPolicy());
    }

    public DeliveryPolicy get(DeliveryType type) {
        if (type == null) {
            throw new IllegalArgumentException("Delivery type must not be null");
        }
        DeliveryPolicy policy = policies.get(type);
        if (policy == null) {
            throw new IllegalArgumentException("No policy registered for type: " + type);
        }
        return policy;
    }
}