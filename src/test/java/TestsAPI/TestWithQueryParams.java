package TestsAPI;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestWithQueryParams {

    @BeforeClass
    public void setupClass() {

        baseURI="http://54.144.70.64:8000";

    }

    /*
        Given accept type is json
        And query parameter values are:
        gender/Female
        nameContains/e
        When user sends in GET request to /api/spartans/search
        Then response status code should be 200
        And response content type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload*/

    @Test
    public void testWithQueryParams() {

        Response response=given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains","J")
                .when().get("/api/spartans/search");



        assertEquals(response.statusCode(), 200);


        assertEquals(response.contentType(), "application/json");

        assertFalse(response.body().asString().contains("Male"));


        assertTrue(response.body().asString().contains("Janette"));

        System.out.println(response.getBody().prettyPrint());


    }
    @Test
    public void testWithQueryParams2() {

        Map<String,Object> mapParam= new HashMap<>();

        mapParam.put("gender","Female");
        mapParam.put("nameContains","J");

        Response response= given().accept(ContentType.JSON)
                .and().queryParams(mapParam)
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);


        assertEquals(response.contentType(), "application/json");

        assertFalse(response.body().asString().contains("Male"));


        assertTrue(response.body().asString().contains("Janette"));

        System.out.println(response.getBody().prettyPrint());






    }
    }
