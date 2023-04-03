package com.cybertek.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init() {

        // save base url inside this variable so that we dont need to
        //type for each test
        RestAssured.baseURI="http://54.152.217.128:1000/ords/hr";

    }
}
