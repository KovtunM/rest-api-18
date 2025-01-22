package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

     /*
        1. Make request (POST) to https://reqres.in/api/login with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
        2. get response: { "token": "QpwL5tke4Pnpja7X4" }
        3. Check token is QpwL5tke4Pnpja7X4
    */
    @Test
    void successfulLoginTest(){
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
    void unsuccessfulLoginTest(){
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
        1. Make request (POST) to https://reqres.in/api/register with body { "email": "eve.holt@reqres.in", "password": "pistol" }
        2. get response: { "id": 4, "token": "QpwL5tke4Pnpja7X4" }
        3. Check id is 4
        4. Check token is QpwL5tke4Pnpja7X4
    */

    @Test
    void successfulRegisterUserTest(){
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
    void unsuccessfulRegisterUserTest(){
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
        1. Make request (POST) to https://reqres.in/api/users with body { "name": "morpheus", "job": "leader" }
        2. get response: { "name": "morpheus", "job": "leader", "id": "868", "createdAt": "2025-01-22T20:08:28.407Z" }
        4. Check name is morpheus
        5. Check job is leader
        6. Check id is 868
        7. Check createdAt is 2025-01-22T20:08:28.407Z
    */

    @Test
    void createUserTest(){
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
}


