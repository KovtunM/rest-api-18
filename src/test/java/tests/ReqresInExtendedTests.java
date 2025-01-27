package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pojo.LoginBodyPojoModel;
import models.pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;

public class ReqresInExtendedTests {

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

    /////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void successfulLoginWithPojoTest() {

        LoginBodyPojoModel loginBodyPojo = new LoginBodyPojoModel();
        loginBodyPojo.setEmail("eve.holt@reqres.in");
        loginBodyPojo.setPassword("cityslicka");

        LoginResponsePojoModel response = given()
                .log().uri()
                .body(loginBodyPojo)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);

//        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithLombokTest() {

        LoginBodyLombokModel loginBodyLombok = new LoginBodyLombokModel();
        loginBodyLombok.setEmail("eve.holt@reqres.in");
        loginBodyLombok.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .log().uri()
                .body(loginBodyLombok)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithAllureTest() {

        LoginBodyLombokModel loginBodyLombok = new LoginBodyLombokModel();
        loginBodyLombok.setEmail("eve.holt@reqres.in");
        loginBodyLombok.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .body(loginBodyLombok)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithCustomAllureTest() {

        LoginBodyLombokModel loginBodyLombok = new LoginBodyLombokModel();
        loginBodyLombok.setEmail("eve.holt@reqres.in");
        loginBodyLombok.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(withCustomTemplates())
                .log().uri()
                .body(loginBodyLombok)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithAllureStepsTest() {

        step("Prepare test data");

        LoginBodyLombokModel loginBodyLombok = new LoginBodyLombokModel();
        loginBodyLombok.setEmail("eve.holt@reqres.in");
        loginBodyLombok.setPassword("cityslicka");

        LoginResponseLombokModel response = step("Make request", () ->
            given()
                    .filter(withCustomTemplates())
                    .log().uri()
                    .body(loginBodyLombok)
                    .contentType(JSON)
                    .when()
                    .post("https://reqres.in/api/login")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginResponseLombokModel.class));

        step("Verify response", () ->
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successfulLoginWithAllureSpecsTest() {

        step("Prepare test data");

        LoginBodyLombokModel loginBodyLombok = new LoginBodyLombokModel();
        loginBodyLombok.setEmail("eve.holt@reqres.in");
        loginBodyLombok.setPassword("cityslicka");

        LoginResponseLombokModel response = step("Make request", () ->
            given(loginRequestSpec)
                    .body(loginBodyLombok)
                    .when()
                    .post()
                    .then()
                    .spec(loginResponseSpec)
                    .extract().as(LoginResponseLombokModel.class));

        step("Verify response", () ->
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }


    @ParameterizedTest()
    @CsvSource(value = {"eve.holt@reqres.in:cityslicka"}, delimiter = ':')
    void successfulLoginParametrizedTest(String login, String password) {

        step("Prepare test data");

        LoginBodyLombokModel loginBodyLombok = new LoginBodyLombokModel();
        loginBodyLombok.setEmail(login);
        loginBodyLombok.setPassword(password);

        LoginResponseLombokModel response = step("Make request", () ->
            given(loginRequestSpec)
                    .body(loginBodyLombok)
                    .when()
                    .post()
                    .then()
                    .spec(loginResponseSpec)
                    .extract().as(LoginResponseLombokModel.class));

        step("Verify response", () ->
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }




}
