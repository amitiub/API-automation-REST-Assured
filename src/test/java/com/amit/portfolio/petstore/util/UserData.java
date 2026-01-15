package com.amit.portfolio.petstore.util;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class UserData {
    private UserData() {}

    public static String uniqueUsername() {
        return "amit_user_" + ThreadLocalRandom.current().nextInt(100000, 999999);
    }

    public static Map<String, Object> newUser(String username) {
        return Map.of(
                "id", ThreadLocalRandom.current().nextInt(100000, 999999),
                "username", username,
                "firstName", "Amit",
                "lastName", "Das",
                "email", username + "@example.com",
                "password", "Password123!",
                "phone", "123-456-7890",
                "userStatus", 1
        );
    }
}
