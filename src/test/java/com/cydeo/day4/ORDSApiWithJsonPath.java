package com.cydeo.day4;

import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import  static io.restassured.RestAssured.*;

public class ORDSApiWithJsonPath extends HrTestBase {

    @DisplayName("Get request to countries")
    @Test
    public void test1(){
        Response response=get("/countries");

        // get the second country name with JsonPath

        //to use jsonpath we assign response to jsonpath
        JsonPath jsonPath= response.jsonPath();
        String secondCountryName=jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id

        List<Object> allCountryIds=jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        //get all country names where their region id is equal to 2


        //get all country names where their region id is equal to 2
        //we implement filter json that we have
        //how you navigate inside the json with an if statement (to filter)--> "items.findAll {it.region_id==2}.country_name"
        //   it:each
        //   check each of the region id
        //   and when the region id is 2, get the country name(value) in return
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryNameWithRegionId2);



        }

        @DisplayName("get request /employees with query param")
        @Test
        public void test2(){

        // we added limit query param to get 107 employees
            Response response=given().queryParam("limit",107)
                    .when().get("/employees");


            JsonPath jsonPath=response.jsonPath();
            List<String> employeeItProgs=jsonPath.getList("items.findAll  {it.job_id==\"IT_PROG\"}.email");
            System.out.println(employeeItProgs);


            // get me first name of employees is making more than 10000

            List<String> empNames=jsonPath.getList("items.findAll  {it.salary>10000}.first_name");
            System.out.println(empNames);


            // get the max  salary first_name
            String kingFirstName= jsonPath.getString("items.max {it.salary}.first_name");
            System.out.println(kingFirstName);



        }

}
