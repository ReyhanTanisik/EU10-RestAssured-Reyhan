package com.cybertek.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithParameters {

    @BeforeAll
    public static void init() {

        // save base url inside this variable so that we dont need to
        //type for each test
        RestAssured.baseURI = "http://54.152.217.128:8000";
    }


    /*
    given accept type is json
    and ıd parameter value is 5
    when user sends get request to api/spartans/{id}
    then response status code should be 200
    and response content type :application/json
    and "blythe " should be in response payload

     */

    @DisplayName("Get request to /api/spartans/{id}")

    @Test
    public void test1(){

        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",5)
                .when().get("/api/spartans/{id}");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Blythe"));
    }


    /*
    task
    given accept type is jason
    and id parameter value is 500
    when user send get request to /api /spartans/{id}
    then response status code should be 404
    and response content - type application/json
    and " not found" message should be displayed in response payload


     */

    @DisplayName("get request /api/spartans/{id}")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .pathParam("id",500)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(404,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));





    }

    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("Get request to api/spartans/search with query parameters")
    @Test
    public void test3(){
     Response response=   given().accept(ContentType.JSON)
                .and().queryParam("nameContains","e")
             .and().queryParam("gender","female")
             .when()
             .get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));



    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4() {
        //create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given().
                log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        //ya response içine queryParam ile key+value ekleyebilirsin.
        // ya da map açıp ona key+value ekleyerek,
        // o map i response içinde queryParams ile ekleyebilirsin. S var dikkat!

        //verify status code 200
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());


        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));





    }






}
