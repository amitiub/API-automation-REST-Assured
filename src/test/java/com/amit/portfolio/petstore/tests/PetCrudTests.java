package com.amit.portfolio.petstore.tests;

import com.amit.portfolio.petstore.core.ApiClient;
import com.amit.portfolio.petstore.util.AssertUtil;
import com.amit.portfolio.petstore.util.TestData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetCrudTests {

    @Test
    public void petCrud_createReadUpdateDelete() {
        long petId = TestData.uniquePetId();

        Response create = ApiClient.post("/pet", TestData.newPet(petId, "AmitPet-" + petId, "available"));
        AssertUtil.assertStatusIn(create, 200, 201);
        AssertUtil.assertJsonFieldPresent(create, "id");

        Response get1 = ApiClient.get("/pet/" + petId);
        AssertUtil.assertStatusIn(get1, 200);
        Assert.assertEquals(get1.jsonPath().getLong("id"), petId);

        Response update = ApiClient.put("/pet", TestData.updatedPet(petId, "AmitPet-" + petId + "-Updated", "sold"));
        AssertUtil.assertStatusIn(update, 200);
        Assert.assertEquals(update.jsonPath().getLong("id"), petId);

        Response get2 = ApiClient.get("/pet/" + petId);
        AssertUtil.assertStatusIn(get2, 200);
        Assert.assertEquals(get2.jsonPath().getString("status"), "sold");

        Response del = ApiClient.delete("/pet/" + petId);
        AssertUtil.assertStatusIn(del, 200, 204);

        Response getDeleted = ApiClient.get("/pet/" + petId);
        AssertUtil.assertStatusIn(getDeleted, 404);
    }
}
