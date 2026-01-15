package com.amit.portfolio.petstore.tests;

import com.amit.portfolio.petstore.core.ApiClient;
import com.amit.portfolio.petstore.util.AssertUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PetNegativeTests {

    @Test
    public void getPet_nonExisting_shouldReturn404() {
        Response r = ApiClient.get("/pet/999999999999"); // very unlikely to exist
        AssertUtil.assertStatusIn(r, 404);
    }

    @Test
    public void createPet_missingName_shouldReturn400or405() {
        // Public demo behavior can vary; we allow 400/405 to keep test meaningful but stable.
        Response r = ApiClient.post("/pet", java.util.Map.of(
                "id", 123456789,
                "status", "available",
                "photoUrls", java.util.List.of("https://example.com/x.jpg")
        ));
        AssertUtil.assertStatusIn(r, 400, 405);
    }

    @Test
    public void updatePet_invalidBody_shouldReturn400or405() {
        Response r = ApiClient.put("/pet", java.util.Map.of("notAValidField", "x"));
        AssertUtil.assertStatusIn(r, 400, 405);
    }
}
