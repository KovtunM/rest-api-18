package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ReqresInTests {

     /*
        1. Make request (POST) to https://reqres.in/api/login with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
        2. get response: { "token": "QpwL5tke4Pnpja7X4" }
        3. Check token is QpwL5tke4Pnpja7X4
    */

    @Test
    void successfulLoginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    /*
        1. Make request (POST) to https://reqres.in/api/login with body { "email": "peter@klaven" }
        2. get response: { "error": "Missing password" }
        3. Check error is Missing password
    */

    @Test
    void unsuccessfulLoginWithMissingPasswordTest() {
        String body = "{ \"email\": \"peter@klaven\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    /*
        1. Make request (POST) to https://reqres.in/api/login with body { "password": "cityslicka" }
        2. get response: { "error": "Missing email or username" }
        3. Check error is Missing email or username
    */

    @Test
    void unsuccessfulLoginWithMissingEmailTest() {
        String body = "{ \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    /*
        1. Make request (POST) to https://reqres.in/api/login with body {  }
        2. get response: { "error": "Missing email or username" }
        3. Check error is Missing email or username
    */

    @Test
    void unsuccessfulLoginWithEmptyDataTest() {
        String body = "{  }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

        /*
        1. Make request (POST) to https://reqres.in/api/login with body {  }
        2. get response: { "error": "Missing email or username" }
        3. Check error is Missing email or username
    */

    @Test
    void unsuccessfulRegisterUserWithEmptyDataTest() {
        String body = "{  }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    /*
      1. Make request (POST) to https://reqres.in/api/register with body { "email": "eve.holt@reqres.in", "password": "pistol" }
      2. get response: { "id": 4, "token": "QpwL5tke4Pnpja7X4" }
      3. Check id is 4
      4. Check token is QpwL5tke4Pnpja7X4
   */

    @Test
    void successfulRegisterUserTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    /*
        1. Make request (POST) to https://reqres.in/api/register with body { "email": "sydney@fife" }
        2. get response: { "error": "Missing password" }
        4. Check error is Missing password
    */

    @Test
    void unsuccessfulRegisterUserWithMissingPasswordTest() {
        String body = "{ \"email\": \"sydney@fife\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

        /*
        1. Make request (POST) to https://reqres.in/api/register with body { "password": "pistol" }
        2. get response: { "error": "Missing email or username" }
        4. Check error is Missing email or username
    */

    @Test
    void unsuccessfulRegisterUserWithMissingEmailTest() {
        String body = "{ \"password\": \"pistol\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    /*
        1. Make request (POST) to https://reqres.in/api/users with body { "name": "morpheus", "job": "leader" }
        2. get response: { "name": "morpheus", "job": "leader", "id": "868", "createdAt": "2025-01-22T20:08:28.407Z" }
        4. Check name is morpheus
        5. Check job is leader
        6. Check id is 868
        7. Check createdAt is 2025-01-22T20:08:28.407Z
    */

    @Test
    void successfulCreateUserTest() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
//                .body("id", is(942))
//                .body("createdAt", is("2025-01-22T20:08:28.407Z"));
    }

    @Test
    void delayedResponseTest() {
        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(1))
                .body("data.find { it.id == 1 }.last_name", equalTo("Bluth"))
                .body("data.find { it.id == 2 }.first_name", equalTo("Janet"))
                .body("data.find { it.id == 2 }.last_name", equalTo("Weaver"))
                .body("data.find { it.id == 3 }.email", equalTo("emma.wong@reqres.in"))
                .body("data.find { it.id == 4 }.avatar", equalTo("https://reqres.in/img/faces/4-image.jpg"))
                .body("data.find { it.id == 5 }", allOf(
                        hasEntry("first_name", "Charles"),
                        hasEntry("last_name", "Morris"),
                        hasEntry("email", "charles.morris@reqres.in"),
                        hasEntry("avatar", "https://reqres.in/img/faces/5-image.jpg")))
                .body("support.url", equalTo("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"))
                .body(matchesJsonSchemaInClasspath("schemes/schemes-reqres-in/delayed-response-schemes.json"));
    }

}


