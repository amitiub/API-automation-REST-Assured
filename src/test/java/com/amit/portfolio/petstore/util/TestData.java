package com.amit.portfolio.petstore.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class TestData {
    private TestData() {}

    public static long uniquePetId() {
        return ThreadLocalRandom.current().nextLong(100_000_000L, 999_999_999L);
    }

    public static Map<String, Object> newPet(long id, String name, String status) {
        return Map.of(
                "id", id,
                "name", name,
                "status", status,
                "photoUrls", List.of("https://example.com/pet/" + id + ".jpg"),
                "tags", List.of(Map.of("id", 1, "name", "portfolio"))
        );
    }

    public static Map<String, Object> updatedPet(long id, String name, String status) {
        return Map.of(
                "id", id,
                "name", name,
                "status", status,
                "photoUrls", List.of("https://example.com/pet/" + id + "-updated.jpg"),
                "tags", List.of(Map.of("id", 2, "name", "updated"))
        );
    }
}
