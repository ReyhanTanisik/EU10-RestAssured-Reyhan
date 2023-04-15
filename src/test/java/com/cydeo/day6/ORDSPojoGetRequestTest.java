package com.cydeo.day6;

import com.cydeo.pojo.*;
import com.cydeo.utilities.*;
import io.restassured.path.json.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class ORDSPojoGetRequestTest extends HrTestBase {

    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }


    @DisplayName("get request to /employees and only get couple of  values as a pojo class")
    @Test
    public void employeeGet() {
        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);


        System.out.println(employee1);
    }


 /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields
     */

    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest() {
        //send a get request and save everthing inside the regions object
        //since we prepare pojo also for the all properties we dont need to use any path so as() method is enough


       Regions regions= get("/regions").then().statusCode(200).extract().response().as(Regions.class);

       assertThat(regions.getCount(),is(4));
       List <String> regionNames= new ArrayList<>();
       List<Integer> regionIds= new ArrayList<>();


       // get list of regions out of regions object
       List <Region> items= regions.getItems();

       for (Region region :items){
           regionIds.add(region.getRId());
           regionNames.add(region.getRegion_name());
       }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        List<Integer> expectedRegionIds= Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        //compare two result
        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));


    }

}