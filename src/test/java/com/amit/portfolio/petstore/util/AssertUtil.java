package com.amit.portfolio.petstore.util;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Set;

public final class AssertUtil {
    private AssertUtil() {}

    public static void assertStatusIn(Response r, int... allowed) {
        Set<Integer> ok = java.util.Arrays.stream(allowed).boxed().collect(java.util.stream.Collectors.toSet());
        Assert.assertTrue(ok.contains(r.statusCode()),
                "Unexpected status " + r.statusCode() + " (allowed " + ok + "). Body: " + safeBody(r));
    }

    public static void assertJsonFieldPresent(Response r, String jsonPath) {
        Object v = r.jsonPath().get(jsonPath);
        Assert.assertNotNull(v, "Expected JSON path present: " + jsonPath + ". Body: " + safeBody(r));
    }

    private static String safeBody(Response r) {
        try { return r.asString(); } catch (Exception e) { return "<unavailable>"; }
    }
}
