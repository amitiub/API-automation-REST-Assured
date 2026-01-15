package com.amit.portfolio.petstore.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.amit.portfolio.petstore.util.LogUtil;
import java.io.PrintStream;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class ApiClient {

    private ApiClient() {}

    private static RequestSpecification spec() {
        RestAssured.baseURI = Config.baseUrl();

        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(RestAssuredConfig.config().httpClient(
                        HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", Config.timeoutMs())
                                .setParam("http.socket.timeout", Config.timeoutMs())
                ))
                .build();
    }

    public static Response get(String path) {
        var req = given().spec(spec());
        if (LogUtil.enabled()) {
            PrintStream out = System.out;
            req = req.filter(LogUtil.requestFilter(out)).filter(LogUtil.responseFilter(out));
        }
        return req
                .when().get(path)
                //.when().get(path)
                .then().extract().response();
    }

    public static Response post(String path, Object body) {
        var req = given().spec(spec()).body(body);
        if (LogUtil.enabled()) {
            PrintStream out = System.out;
            req = req.filter(LogUtil.requestFilter(out)).filter(LogUtil.responseFilter(out));
        }
        return req
                .when().post(path)
                .then().extract().response();
    }

    public static Response put(String path, Object body) {
        var req = given().spec(spec()).body(body);
        if (LogUtil.enabled()) {
            PrintStream out = System.out;
            req = req.filter(LogUtil.requestFilter(out)).filter(LogUtil.responseFilter(out));
        }
        return req
                .when().put(path)
                .then().extract().response();
    }

    public static Response delete(String path) {
        var req = given().spec(spec());
        if (LogUtil.enabled()) {
            PrintStream out = System.out;
            req = req.filter(LogUtil.requestFilter(out)).filter(LogUtil.responseFilter(out));
        }
        return req
                .when().delete(path)
                .then().extract().response();
    }
}
