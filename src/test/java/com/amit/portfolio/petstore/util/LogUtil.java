package com.amit.portfolio.petstore.util;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.io.PrintStream;

/**
 * Central place to control REST Assured request/response logging.
 * Enable by running with: -DapiLog=true
 */
public final class LogUtil {
    private LogUtil() {}

    public static boolean enabled() {
        return Boolean.parseBoolean(System.getProperty("apiLog", "false"));
    }

    public static RequestLoggingFilter requestFilter(PrintStream out) {
        return new RequestLoggingFilter(out);
    }

    public static ResponseLoggingFilter responseFilter(PrintStream out) {
        return new ResponseLoggingFilter(out);
    }
}
