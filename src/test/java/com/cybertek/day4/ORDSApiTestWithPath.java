package com.cybertek.day4;

import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class ORDSApiTestWithPath extends HrTestBase {


    @DisplayName("get request to countries with path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");


        assertEquals(200, response.statusCode());


        // print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first countryId

        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // find the href http://54.152.217.128:1000/ords/hr/countries/CA
        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);


        //  get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);


        // assert that all regions ids are equal to 2

        List<Integer> allRegionsIds = response.path("items.region_id");

        for (Integer regionsId : allRegionsIds) {
            System.out.println("regionsId = " + regionsId);
            assertEquals(2, regionsId);
        }

    }


    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all()
                .when()
                .get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));


        List<String> allJobIds=response.path("items.job_id");
        for (String jobId :allJobIds){
            System.out.println("jobId = " + jobId);
            assertEquals("IT_PROG",jobId);
        }
    }


}