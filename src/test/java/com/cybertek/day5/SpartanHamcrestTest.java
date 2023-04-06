package com.cybertek.day5;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import  static io.restassured.RestAssured.*;
public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("Get spartan/search and chaining together")
    @Test
    public void test1(){
    List<String> names= given().accept(ContentType.JSON)
                .and().queryParams("nameContains","j",
                        "gender","male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .and().body("totalElement",is(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names);



    }



}
