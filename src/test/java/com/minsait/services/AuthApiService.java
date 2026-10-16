package com.minsait.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApiService {

    private static final String BASE_URI = "https://reqres.in/api";

    public AuthApiService() {
        RestAssured.baseURI = BASE_URI;
    }

    public Response autenticarUsuario(String email, String password) {
        String payloadJson = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        return given()
                .filter(new AllureRestAssured())
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(payloadJson)
                .when()
                .post("/login")
                .then()
                .extract()
                .response();
    }

    public String obterTokenAutenticacao(String email, String password) {
        Response response = autenticarUsuario(email, password);
        return response.jsonPath().getString("token");
    }
}