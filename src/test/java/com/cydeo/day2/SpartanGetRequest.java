package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String baseUrl="http://54.152.217.128:8000";


    //given accept type application/json,we are adding header
    //when user send get request to api/spartans end point
    //then status code must be 200
    //then response content type must be application/json
    //and response body should include spartan result


    @Test
    public void test1(){

      Response response=  RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl+"/api/spartans");
        //printing status code from response object


        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());


        // print whole response body
        response.prettyPrint();

        //how to do Api testing then
        Assertions.assertEquals(response.statusCode(),200);

        // verify content type is application/json
        Assertions.assertEquals(response.contentType(),"application/json");

        //given accept type application/json,we are adding header
        //when user send get request to api/spartans/3
        //then status code must be 200
        //then response content type must be application/json
        //and response body should include spartan result




        }

    @DisplayName("Get one spartan /api/spartan/3 and verify")
    @Test
    public void test2(){

        //given accept contentType.json we are adding header

        //when user send get request
        Response response =RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl+"/api/spartans/3");

        //verify status code 200


        Assertions.assertEquals(response.statusCode(),200);


        //verify content type
        Assertions.assertEquals(response.contentType(),"application/json");


        //verify json body contains fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));



    }

    /*
    given no headers provided
    when users sends get request to /api/hello
    the response status code should be 200
    and content type header should be "text/plain;charset=UTF-8"
    and header should contain date
    and content -length should be 17
    and body should be "hello sparta"




     */


    @DisplayName("get request to /api/hello")
    @Test
    public void test3(){

        //no headers provided here
        Response response=RestAssured.when().get(baseUrl+"/api/hello");

        //verify status code
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        // verify we have headers named date
        // we use hasHeaderWithName method to verify header exists or not



        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //how to get and header from response using header key
        //we use response.header(String headerName) method to get any header value

        // we get header value here , as string
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        // we do verifications of header value
        Assertions.assertEquals("17",response.header("Content-Length"));
        Assertions.assertEquals(response.body().asString(),"Hello from Sparta");
    }


    }
