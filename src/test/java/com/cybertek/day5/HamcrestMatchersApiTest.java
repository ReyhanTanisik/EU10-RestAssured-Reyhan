package com.cybertek.day5;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestMatchersApiTest {
 /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when()
                .get("http://54.152.217.128:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),
                        "name",is("Meta")
                        ,"gender",is("Female"),
                        "phone",is(1938695106));

        






    }

}