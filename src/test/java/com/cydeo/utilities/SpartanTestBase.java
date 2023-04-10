package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {

    @BeforeAll
    public static void init() {

        // save base url inside this variable so that we dont need to
        //type for each test
        RestAssured.baseURI = "http://54.152.217.128:8000";



        String dbUrl="jdbc:oracle:thin:@54.152.217.128:1521:XE";
        String dbUsername="SP";
        String dbPassword="SP";

        //DBUtils.createConnection(dbUrl,dbUsername,dbPassword);

    }

    @AfterAll
    public static void teardown(){
       // DBUtils.destroy();
    }
}
