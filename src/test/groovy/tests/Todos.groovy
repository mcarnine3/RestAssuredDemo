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

@Feature("Todos")
class Todos extends Base {

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Verify that /todos is returning a 200 response", groups = "smoke", priority = 0)
    @Description("Does a get to /todos and asserts a 200 response with a correct schema response")
    void checkTodosReturns200() {
        Response response = get(Endpoints.getEndpoint("todos"))
        int responseCode = response.then().extract().statusCode()
        Assert.assertEquals(200, responseCode)
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + "todos-200.json"))
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Verify that /todos is returning the correct amount of items", groups = "regression", priority = 1)
    @Description("Does a get to /todos and asserts that 200 items are returned")
    void checkTodosReturnsCorrectAmountOfItems() {
        int expected = 200
        Response response = get(Endpoints.getEndpoint("todos"))
        int responseCode = response.then().extract().statusCode()
        Assert.assertEquals(200, responseCode)
        ArrayList<String> allTodos = response.path("id")
        Assert.assertTrue(allTodos.size() == expected, "Wrong amount of todos returned. Expected: ${expected} - Returned: ${allTodos.size()}")
    }

}
