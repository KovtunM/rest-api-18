package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTests {
    /*
        1. Make request to https://selenoid.autotests.cloud/status
        2. get response {"total":20,"used":0,"queued":0,"pending":0,"browsers":{
                "chrome":{"100.0":{},"113.0":{},"114.0":{},"120.0":{},"121.0":{},"122.0":{},"123.0":{},"124.0":{},"125.0":{},"126.0":{},"99.0":{}},
                "firefox":{"122.0":{},"123.0":{},"124.0":{},"125.0":{}},"opera":{"106.0":{},"107.0":{},"108.0":{},"109.0":{}},
                "playwright-chromium":{"1.28.1":{}}}}
        3. Check total is 20
    */

    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkWithGivenTotal() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkWithStatusTotal() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkWithAllLogsTotal() {
        given()
                .log().all()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkWithSomeLogsTotal() {
        given()
                .log().uri()
                //.log().body()   // for post request
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkChromeVersion() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"));
    }

    @Test
    void checkResponseBadPractice() {  //BAD PRACTICE. todo move to models
        String expectedResponse = "{\"total\":20,\"used\":0,\"queued\":0,\"pending\":0,\"browsers\":" +
                "{\"chrome\":{\"100.0\":{},\"113.0\":{},\"114.0\":{},\"120.0\":{},\"121.0\":{},\"122.0\":{}," +
                "\"123.0\":{},\"124.0\":{},\"125.0\":{},\"126.0\":{},\"99.0\":{}},\"" +
                "firefox\":{\"122.0\":{},\"123.0\":{},\"124.0\":{},\"125.0\":{}},\"" +
                "opera\":{\"106.0\":{},\"107.0\":{},\"108.0\":{},\"109.0\":{}},\"" +
                "playwright-chromium\":{\"1.28.1\":{}}}}\n";

        String actualResponse = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response().asString();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void checkResponseGoodPractice() {
        Integer expectedTotal = 20;

        Integer actualTotal = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("total");

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    void checkJsonScheme() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"))
                .body(matchesJsonSchemaInClasspath("schemes/schemes-selenoid/selenoid-status-scheme-response.json"));
    }
}



