package tests

import common.Endpoints
import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.testng.Tag
import io.qameta.allure.testng.Tags
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test
import static io.restassured.RestAssured.*

@Feature("Users")
class Users extends Base {

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Verify that /users is returning a 200 response", groups = "smoke", priority = 0)
    @Description("Does a get to /users and asserts a 200 response with a correct schema response")
    @Tag("critical")
    void checkUsersReturns200() {
        Response response = get(Endpoints.getEndpoint("users"))
        int responseCode = response.then().extract().statusCode()
        Assert.assertEquals(200, responseCode)
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + "users-200.json"))
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Verify that /users is returning the correct amount of items", groups = "regression", priority = 1)
    @Description("Does a get to /users and asserts that 10 items are returned")
    void checkUsersReturnsCorrectAmountOfItems() {
        int expected = 10
        Response response = get(Endpoints.getEndpoint("users"))
        int responseCode = response.then().extract().statusCode()
        Assert.assertEquals(200, responseCode)
        ArrayList<String> allUsers = response.path("username")
        Assert.assertTrue(allUsers.size() == expected, "Wrong amount of users returned. Expected: ${expected} - Returned: ${allUsers.size()}")
    }

}
