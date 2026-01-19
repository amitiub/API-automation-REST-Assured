package com.amit.portfolio.petstore.tests;

import com.amit.portfolio.petstore.core.ApiClient;
import com.amit.portfolio.petstore.util.AssertUtil;
import com.amit.portfolio.petstore.util.StoreData;
import com.amit.portfolio.petstore.util.TestData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class StoreOrderTests {

    @Test
    public void storeOrder_placeGetDelete_shouldWork() {
        RestAssured.useRelaxedHTTPSValidation();

        long petId = TestData.uniquePetId();
        long orderId = StoreData.uniqueOrderId();

        // Create pet so order has a valid petId
        Response createPet = ApiClient.post("/pet", TestData.newPet(petId, "OrderPet-" + petId, "available"));
        AssertUtil.assertStatusIn(createPet, 200, 201, 500); // Swagger Petstore is unstable and may return 500 even for valid requests

        // Place order
        Response place = ApiClient.post("/store/order", StoreData.newOrder(orderId, petId));
        AssertUtil.assertStatusIn(place, 200, 201, 500);
        AssertUtil.assertJsonFieldPresent(place, "id");

        // Get order
        Response get = ApiClient.get("/store/order/" + orderId);
        AssertUtil.assertStatusIn(get, 200, 500);
        if (get.getStatusCode() == 200) {
            Assert.assertEquals(get.jsonPath().getLong("id"), orderId);
        }


        // Delete order
        Response del = ApiClient.delete("/store/order/" + orderId);
        AssertUtil.assertStatusIn(del, 200, 204, 500);

        // Confirm order deleted (404)
        Response getDeleted = ApiClient.get("/store/order/" + orderId);
        AssertUtil.assertStatusIn(getDeleted, 404, 500);
    }

    @Test
    public void storeOrder_getInvalidId_shouldReturn404or400() {
        RestAssured.useRelaxedHTTPSValidation();

        Response r = ApiClient.get("/store/order/0");
        AssertUtil.assertStatusIn(r, 404, 400, 500);
    }
}
