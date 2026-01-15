package com.amit.portfolio.petstore.core;

public final class Config {
    private Config() {}

    public static String baseUrl() {
        return sys("baseUrl", "https://petstore3.swagger.io/api/v3");
    }

    public static int timeoutMs() {
        return Integer.parseInt(sys("timeoutMs", "20000"));
    }

    private static String sys(String key, String def) {
        String v = System.getProperty(key);
        if (v == null || v.trim().isEmpty()) return def;
        return v.trim();
    }
}
