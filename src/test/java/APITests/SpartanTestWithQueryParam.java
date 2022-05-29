package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class SpartanTestWithQueryParam {

    @BeforeClass
    public void setupClass() {

        RestAssured.baseURI= "http://18.234.174.132:8000";

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
        And "Janette" should be in response payload


     */

    @Test
    public void queryPramTest1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);


        assertEquals(response.contentType(), "application/json");

        assertFalse(response.body().asString().contains("Male"));


        assertTrue(response.body().asString().contains("Janette"));

        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void testing2(){

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "J");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParams(paramsMap)
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);


        assertEquals(response.contentType(), "application/json");

        assertFalse(response.body().asString().contains("Male"));


        assertTrue(response.body().asString().contains("Janette"));

        System.out.println(response.getBody().prettyPrint());
    }



}
