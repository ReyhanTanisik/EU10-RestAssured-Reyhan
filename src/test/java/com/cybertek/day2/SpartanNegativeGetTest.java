package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init() {

        // save base url inside this variable so that we dont need to
        //type for each test
        RestAssured.baseURI = "http://54.152.217.128:8000";
    }

    /*
    given accept type application/xml
    when user send get request to api/spartans/10 end point
    then status code must be 406
    and response content type must be application/xml;charset=UTF-8
     */


    @DisplayName(" get request to api/spartans/10")
    @Test
    public void test1(){
        Response response=given().
                accept(ContentType.XML).
                when().
                get("api/spartans/10");


        assertEquals(406,response.statusCode());
        assertEquals("application/xml;charset=UTF-8",response.contentType());
    }
}