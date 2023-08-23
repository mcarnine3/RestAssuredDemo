package tests

import common.Endpoints
import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test
import static io.restassured.RestAssured.*

@Feature("Posts")
class Posts extends Base {

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Verify that /posts is returning a 200 response", groups = "smoke", priority = 0)
    @Description("Does a get to /posts and asserts a 200 response with a correct schema response")
    void checkPostsReturns200() {
        Response response = get(Endpoints.getEndpoint("posts"))
        int responseCode = response.then().extract().statusCode()
        Assert.assertEquals(200, responseCode)
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + "posts-200.json"))
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Verify that /posts is returning the correct amount of items", groups = "regression", priority = 1)
    @Description("Does a get to /posts and asserts that 100 items are returned")
    void checkPostsReturnsCorrectAmountOfItems() {
        int expected = 100
        Response response = get(Endpoints.getEndpoint("posts"))
        int responseCode = response.then().extract().statusCode()
        Assert.assertEquals(200, responseCode)
        ArrayList<String> allPosts = response.path("id")
        Assert.assertTrue(allPosts.size() == expected, "Wrong amount of posts returned. Expected: ${expected} - Returned: ${allPosts.size()}")
    }

}
