package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class HrGetRequest {

    // before all needs to be a static method
  @BeforeAll
    public static void init(){

      // save base url inside this variable so that we dont need to
      //type for each test
      RestAssured.baseURI="http://54.152.217.128:1000/ords/hr";


  }
  @DisplayName("Get request to /regions")
  @Test
    public void test1(){
      Response response=RestAssured.get("/regions");

      //print the status code
      System.out.println(response.statusCode());
  }

  /*
  give accept type is application/json
  when user sends get request to regions/2
  then response status code must be 200
   and content type equals to application/json
   and response body contains Americas
   */

    @DisplayName(" get request to /regions/2")
    @Test
    public void test2(){
        Response response=given().accept(ContentType.JSON)
                        .when()
                                .get("/regions/2");
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        response.prettyPrint();
        Assertions.assertTrue((response.body().asString().contains("Americas")));

    }


}
