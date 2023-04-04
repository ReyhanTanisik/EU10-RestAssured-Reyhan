package com.cybertek.day4;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CTApiJsonPath {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI = "https://api.training.cydeo.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1() {
        //send a get request to student id 2 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Mark
                batch 13
                section 100000
                emailAddress mark@gmail.com
                companyName Cydeo
                state Virginia
                zipCode 33222
                using JsonPath
             */


    }
}