package com.amit.portfolio.petstore.util;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class StoreData {
    private StoreData() {}

    public static long uniqueOrderId() {
        return ThreadLocalRandom.current().nextLong(100_000_000L, 999_999_999L);
    }

    public static Map<String, Object> newOrder(long orderId, long petId) {
        return Map.of(
                "id", orderId,
                "petId", petId,
                "quantity", 1,
                "shipDate", Instant.now().toString(),
                "status", "placed",
                "complete", false
        );
    }
}
