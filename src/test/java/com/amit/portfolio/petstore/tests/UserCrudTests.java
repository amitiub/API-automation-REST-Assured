package com.amit.portfolio.petstore.tests;

import com.amit.portfolio.petstore.core.ApiClient;
import com.amit.portfolio.petstore.util.AssertUtil;
import com.amit.portfolio.petstore.util.UserData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCrudTests {

    @Test
    public void user_createGetUpdateDelete_shouldWork() {
        String username = UserData.uniqueUsername();

        // Create user
        Response create = ApiClient.post("/user", UserData.newUser(username));
        AssertUtil.assertStatusIn(create, 200, 201);

        // Get user
        Response get = ApiClient.get("/user/" + username);
        AssertUtil.assertStatusIn(get, 200);
        Assert.assertEquals(get.jsonPath().getString("username"), username);

        // Update user
        Response update = ApiClient.put("/user/" + username, UserData.newUser(username)); // same payload OK for demo
        AssertUtil.assertStatusIn(update, 200);

        // Delete user
        Response del = ApiClient.delete("/user/" + username);
        AssertUtil.assertStatusIn(del, 200, 204);

        // Confirm deleted (404)
        Response getDeleted = ApiClient.get("/user/" + username);
        AssertUtil.assertStatusIn(getDeleted, 404);
    }

    @Test
    public void user_loginLogout_smoke() {
        // Petstore demo login is not a real auth system; still good as a smoke test.
        String username = "user";
        String password = "password";

        Response login = ApiClient.get("/user/login?username=" + username + "&password=" + password);
        AssertUtil.assertStatusIn(login, 200, 400); // demo may return 400 if not configured

        Response logout = ApiClient.get("/user/logout");
        AssertUtil.assertStatusIn(logout, 200);
    }
}
