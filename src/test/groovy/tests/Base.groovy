package tests

import com.google.common.collect.ImmutableMap
import common.BasePath
import common.Environment
import config.Configuration
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeSuite

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter

class Base {

    @BeforeSuite(alwaysRun=true)
    static void setupEnvironment() {
        Configuration.loadAllConfigs()

        String tags = System.getProperty("tags");
        if (tags == null || tags.isEmpty()) {
            tags = "ALL";
        }
        String testRun = System.getProperty("testRunID")
        HashMap<String, String> properties = new HashMap<String, String>()
        properties.put("Environment:", Environment.getEnvironment().toUpperCase())
        if (testRun != null) {
            properties.put("Test Run:", "https://project.testrail.io/index.php?/runs/view/" + testRun)
        }
        properties.put("Base url:", BasePath.getBasePath())
        properties.put("Tags:", tags)
        ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(properties)
        allureEnvironmentWriter(immutableMap, System.getProperty("user.dir") + "/build/allure-results/")
    }

    @BeforeClass(alwaysRun=true)
    static void setupRestAssured() {
        RestAssured.baseURI = BasePath.getBasePath()
        RestAssured.basePath = "/"
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                addHeader("Content-Type", ContentType.JSON.toString()).
                addHeader("Accept", ContentType.JSON.toString())
                .build()
        RestAssured.requestSpecification = requestSpecification
    }
}
